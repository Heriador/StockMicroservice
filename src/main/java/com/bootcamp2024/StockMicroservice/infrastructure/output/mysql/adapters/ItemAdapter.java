package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.util.SortBy;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IItemEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.PaginationMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.ItemEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class ItemAdapter implements IItemPersistencePort {

    private final IItemRepository itemRepository;
    private final IItemEntityMapper itemEntityMapper;
    private final PaginationMapper paginationMapper;

    @Override
    public void saveItem(Item item) {
        itemRepository.save(itemEntityMapper.itemToItemEntity(item));
    }

    @Override
    public Optional<Item> findByName(String itemName) {

        Optional<ItemEntity> itemEntity = itemRepository.findByName(itemName);

        return itemEntity.map(itemEntityMapper::itemEntityToItem);
    }

    @Override
    public Optional<Item> findById(Long itemId) {

        Optional<ItemEntity> itemEntity = itemRepository.findById(itemId);

        return itemEntity.map(itemEntityMapper::itemEntityToItem);
    }

    @Modifying
    @Override
    public void addStock(Item item, int quantity) {
        item.setStock(item.getStock() + quantity);

        itemRepository.save(itemEntityMapper.itemToItemEntity(item));
    }

    @Override
    public Optional<PaginationCustom<Item>> getAllItems(int page, int size, String sortParam, boolean ord) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ItemEntity> itemEntityPage;


        if(SortBy.CATEGORY.getFieldName().equalsIgnoreCase(sortParam)){
            itemEntityPage = ord ?
                          itemRepository.findAllOrderByCategoryCountAsc(pageable)
                        : itemRepository.findAllOrderByCategoryCountDesc(pageable);
        }
        else if(SortBy.NAME.getFieldName().equalsIgnoreCase(sortParam)){
            itemEntityPage = ord ?
                          itemRepository.findAllOrderByNameAsc(pageable)
                        : itemRepository.findAllOrderByNameDesc(pageable);

        }
        else if(SortBy.BRAND.getFieldName().equalsIgnoreCase(sortParam)){
            itemEntityPage = ord ?
                          itemRepository.findAllOrderByBrandCountAsc(pageable)
                        : itemRepository.findAllOrderByBrandCountDesc(pageable);
        }
        else{
            itemEntityPage = itemRepository.findAll(pageable);
        }

        PaginationCustom<Item> itemPaginationCustom = paginationMapper.toItemPaginationCustom(itemEntityPage);



        return Optional.of(itemPaginationCustom);
    }

    @Override
    public Optional<PaginationCustom<Item>> getItemsPaginatedById(Integer page, Integer size, Boolean ord, String sortBy, List<Long> itemIds, String filterByCategoryName, String filterByBrandName) {

        Sort sort = Boolean.TRUE.equals(ord) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ItemEntity> itemEntityPage = itemRepository.findAllByIdInAndFilters(itemIds,filterByBrandName, filterByCategoryName ,pageable);
        PaginationCustom<Item> itemPaginationCustom = paginationMapper.toItemPaginationCustom(itemEntityPage);

        return Optional.of(itemPaginationCustom);
    }

    @Override
    public Boolean existsById(Long itemId) {
        return itemRepository.existsById(itemId);
    }

    @Override
    public Boolean hasStock(Long itemId, Long quantity) {
        return itemRepository.existsByIdAndStockGreaterThanEqual(itemId, quantity);
    }
}
