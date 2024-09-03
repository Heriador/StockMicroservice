package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
public
 interface ICategoryServicePort {
    void saveCategory(Category category);

    PaginationCustom<Category> getAllCategories(int page, int size, boolean ord);

    Category findByName(String categoryName);

    Category getCategory(Long categoryId);

}
