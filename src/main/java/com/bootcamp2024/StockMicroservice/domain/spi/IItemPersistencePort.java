package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;

import java.util.Optional;

public interface IItemPersistencePort {
    void saveItem(Item item);

    Optional<Item> findByName(String itemName);

    Optional<Item> findById(Long itemId);

    void addStock(Item item, int quantity);

    Optional<PaginationCustom<Item>> getAllItems(int page, int size, String sortParam, boolean ord);

    Boolean existsById(Long itemId);

    Boolean hasStock(Long itemId, Long quantity);
}
