package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IItemEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.ItemEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ItemAdapter implements IItemPersistencePort {

    private final IItemRepository itemRepository;
    private final IItemEntityMapper itemEntityMapper;

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
}
