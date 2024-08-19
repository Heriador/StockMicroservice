package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.CategoryResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Category;

import java.util.List;

public interface ICategoryHandler {

    void createCategory(AddCategory addCategory);

    List<CategoryResponse> getAllcategories();

    CategoryResponse getCategory(String categoryName);


}
