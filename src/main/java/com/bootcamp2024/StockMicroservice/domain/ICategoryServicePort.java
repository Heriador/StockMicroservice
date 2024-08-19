package com.bootcamp2024.StockMicroservice.domain;

import com.bootcamp2024.StockMicroservice.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategory(String categoryName);


}
