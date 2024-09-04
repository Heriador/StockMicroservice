package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Item;

public interface IItemServicePort {
    void saveItem(Item item);

    Item findByName(String itemName);

    Item findById(Long itemId);
}
