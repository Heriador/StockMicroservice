package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;

public interface IItemHandler {
    void createItem(AddItem addItem);

    ItemResponse findByName(String itemName);

    ItemResponse findById(Long itemId);

}
