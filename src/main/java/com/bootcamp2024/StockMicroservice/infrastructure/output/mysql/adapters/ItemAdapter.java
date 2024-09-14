package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;
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

        sortParam = sortParam.contains("name") ? "name" : sortParam.concat(".name");
        Sort sort = ord ? Sort.by(sortParam).ascending() : Sort.by(sortParam).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ItemEntity> itemEntityPage = itemRepository.findAll(pageable);


        PaginationCustom<Item> itemPaginationCustom = paginationMapper.toItemPaginationCustom(itemEntityPage);



        return Optional.of(itemPaginationCustom);
    }

}
