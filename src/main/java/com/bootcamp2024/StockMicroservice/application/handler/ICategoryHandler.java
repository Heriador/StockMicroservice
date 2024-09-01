package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.GetAllCategories;

public interface ICategoryHandler {

    void createCategory(AddCategory addCategory);

    GetAllCategories getAllcategories(int page, int size);

    CategoryResponse getCategory(String categoryName);


}
