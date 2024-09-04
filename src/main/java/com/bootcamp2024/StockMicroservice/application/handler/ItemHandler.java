package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemHandler implements IItemHandler{


    private final IItemServicePort itemServicePort;

    private final IItemRequestMapper itemRequestMapper;
    private final IItemResponseMapper itemResponseMapper;


    @Override
    public void createItem(AddItem addItem) {
        Item item = itemRequestMapper.toItem(addItem);

        itemServicePort.saveItem(item);
    }

    @Override
    public ItemResponse findByName(String itemName) {

        Item item = itemServicePort.findByName(itemName);

        return itemResponseMapper.toItemResponse(item);
    }

    @Override
    public ItemResponse findById(Long itemId) {
        Item item = itemServicePort.findById(itemId);

        return itemResponseMapper.toItemResponse(item);
    }
}
