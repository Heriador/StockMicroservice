package com.bootcamp2024.StockMicroservice.Factory;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemCategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.ItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class ItemFactory {

    private static final Item item;
    private static final ItemEntity itemEntity;
    private static final PaginationCustom<Item> paginationCustom;
    private static final AddItem addItem;
    private static final ItemResponse itemResponse;
    private static final PaginationResponse<ItemResponse> paginationResponse;
    private static final ItemCategoryResponse itemCategoryResponse;

    static {
        item = new Item();
        item.setId(1L);
        item.setName("manzana pinto");
        item.setDescription("manzana pintosa");
        item.setPrice(BigDecimal.valueOf(18.9));
        item.setStock(10L);
        item.setBrand(createBrand());
        item.setCategories(List.of(createCategory(1L, "Category1","description"), createCategory(2L, "Category2","description")));

        addItem = new AddItem();
        addItem.setName(item.getName());
        addItem.setDescription(item.getDescription());
        addItem.setPrice(item.getPrice());
        addItem.setStock(item.getStock());
        addItem.setBrandId(item.getBrand().getId());
        addItem.setCategories(List.of(1L, 2L));

        itemEntity = new ItemEntity();
        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setStock(item.getStock());
        itemEntity.setBrand(BrandFactory.getBrandEntity());
        itemEntity.setCategories(List.of(CategoryFactory.getCategoryEntity()));

        itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setName(item.getName());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setPrice(item.getPrice());
        itemResponse.setStock(item.getStock());
        itemResponse.setBrand(BrandFactory.getBrandResponse());

        itemCategoryResponse = new ItemCategoryResponse();
        itemCategoryResponse.setId(item.getId());
        itemCategoryResponse.setName(item.getName());

        itemResponse.setCategories(List.of(itemCategoryResponse));

        paginationCustom = new PaginationCustom<>(List.of(item), 0, 1, 1L, 1, true);

        paginationResponse = new PaginationResponse<>();
        paginationResponse.setTotalPages(paginationCustom.getTotalPages());
        paginationResponse.setTotalElements(paginationCustom.getTotalElements());
        paginationResponse.setLast(paginationCustom.isLast());
        paginationResponse.setContent(List.of(itemResponse));
        paginationCustom.setPageNumber(paginationCustom.getPageNumber());
        paginationCustom.setPageSize(paginationCustom.getPageSize());

    }

    public static Item getItem() {
        return item;
    }

    public static ItemEntity getItemEntity() {
        return itemEntity;
    }

    public static AddItem getAddItem() {
        return addItem;
    }

    public static ItemResponse getItemResponse() {
        return itemResponse;
    }

    public static PaginationCustom<Item> getPaginationCustom() {
        return paginationCustom;
    }

    public static PaginationResponse<ItemResponse> getPaginationResponse() {
        return paginationResponse;
    }

    private static Brand createBrand() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("BrandName");
        return brand;
    }

    private static Category createCategory(Long id, String name, String description) {

        return new Category(id, name,description);
    }

}
