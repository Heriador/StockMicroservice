package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.GetAllCategories;
import com.bootcamp2024.StockMicroservice.domain.model.Category;

import java.util.List;

public interface ICategoryHandler {

    void createCategory(AddCategory addCategory);

    GetAllCategories getAllcategories(int page, int size, boolean ord);

    CategoryResponse getCategory(String categoryName);


}
