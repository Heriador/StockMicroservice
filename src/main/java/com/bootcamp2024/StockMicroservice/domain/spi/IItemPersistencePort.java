package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Item;

import java.util.Optional;

public interface IItemPersistencePort {
    void saveItem(Item item);

    Optional<Item> findByName(String itemName);

    Optional<Item> findById(Long itemId);
}
