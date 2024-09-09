package com.bootcamp2024.StockMicroservice.Factory;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;

import java.math.BigDecimal;
import java.util.List;

public class ItemFactory {

    private static final Item item;
    private static final PaginationCustom<Item> paginationCustom;

    static {
        item = new Item();
        item.setId(1L);
        item.setName("manzana pinto");
        item.setDescription("manzana pintosa");
        item.setPrice(BigDecimal.valueOf(18.9));
        item.setStock(10L);
        item.setBrand(createBrand());
        item.setCategories(List.of(createCategory(1L, "Category1","description"), createCategory(2L, "Category2","description")));

        paginationCustom = new PaginationCustom<>(List.of(item), 0, 1, 1L, 1, true);
    }

    public static Item getItem() {
        return item;
    }

    public static PaginationCustom<Item> getPaginationCustom() {
        return paginationCustom;
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
