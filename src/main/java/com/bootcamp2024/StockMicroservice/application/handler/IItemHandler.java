package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.request.AddStock;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;

import java.math.BigDecimal;
import java.util.List;

public interface IItemHandler {
    void createItem(AddItem addItem);

    ItemResponse findByName(String itemName);

    ItemResponse findById(Long itemId);

    PaginationResponse<ItemResponse> getAllItems(int page, int size, String sortParam, boolean ord);

    void addStock(Long itemId, AddStock addStock);

    Boolean existsById(Long itemId);

    Boolean hasStock(Long itemId, Long quantity);

    List<String> getCategories(Long itemId);

    PaginationResponse<ItemResponse> getItemsPaginatedById(Integer page, Integer size, Boolean ord, String sortBy,List<Long> itemIds, String filterByCategoryName, String filterByBrandName);

    BigDecimal getPriceById(Long itemId);

    void sellItem(Long itemId, AddStock addStock);
}
