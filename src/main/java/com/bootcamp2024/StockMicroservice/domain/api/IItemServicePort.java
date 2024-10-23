package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;

import java.math.BigDecimal;
import java.util.List;

public interface IItemServicePort {
    void saveItem(Item item);

    Item findByName(String itemName);

    Item findById(Long itemId);

    void addStock(Long itemId, int quantity);

    PaginationCustom<Item> getAllItems(int page, int size, String sortParam, boolean ord);

    Boolean existsById(Long itemId);

    Boolean hasStock(Long itemId, Long quantity);

    List<String> getCategories(Long itemId);

    PaginationCustom<Item> getItemsPaginatedById(Integer page, Integer size, Boolean ord,String sortBy, List<Long> itemIds, String filterByCategoryName, String filterByBrandName);

    BigDecimal getPriceById(Long itemId);

    void sellItem(Long itemId, Integer quantity);
}
