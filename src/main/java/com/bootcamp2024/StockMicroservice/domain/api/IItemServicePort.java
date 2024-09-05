package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;

public interface IItemServicePort {
    void saveItem(Item item);

    Item findByName(String itemName);

    Item findById(Long itemId);

    PaginationCustom<Item> getAllItems(int page, int size, String sortParam, boolean ord);
}
