package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.request.AddStock;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemResponseMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.PaginationResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemHandler implements IItemHandler{


    private final IItemServicePort itemServicePort;

    private final IItemRequestMapper itemRequestMapper;
    private final IItemResponseMapper itemResponseMapper;
    private final PaginationResponseMapper paginationResponseMapper;


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

    @Override
    public PaginationResponse<ItemResponse> getAllItems(int page, int size, String sortParam, boolean ord) {

        PaginationCustom<Item> itemPaginationCustom = itemServicePort.getAllItems(page, size, sortParam, ord);

        return paginationResponseMapper.toItemPaginationResponse(itemPaginationCustom);
    }

    @Override
    public void addStock(Long itemId, AddStock addStock) {
        itemServicePort.addStock(itemId, addStock.getQuantity());
    }

    @Override
    public Boolean existsById(Long itemId) {
        return itemServicePort.existsById(itemId);
    }

    @Override
    public Boolean hasStock(Long itemId, Long quantity) {
        return itemServicePort.hasStock(itemId, quantity);
    }

    @Override
    public List<String> getCategories(Long itemId) {
        return itemServicePort.getCategories(itemId);
    }

    @Override
    public PaginationResponse<ItemResponse> getItemsPaginatedById(Integer page, Integer size, Boolean ord,String sortBy, List<Long> itemIds, String filterByCategoryName, String filterByBrandName) {
        PaginationCustom<Item> itemPaginationCustom = itemServicePort.getItemsPaginatedById(page, size, ord,sortBy, itemIds, filterByCategoryName, filterByBrandName);

        return paginationResponseMapper.toItemPaginationResponse(itemPaginationCustom);
    }

    @Override
    public BigDecimal getPriceById(Long itemId) {
        return itemServicePort.getPriceById(itemId);
    }

    @Override
    public void sellItem(Long itemId, AddStock addStock) {
        itemServicePort.sellItem(itemId, addStock.getQuantity());
    }
}
