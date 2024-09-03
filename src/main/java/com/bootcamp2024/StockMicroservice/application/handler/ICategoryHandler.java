package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;

public interface ICategoryHandler {

    void createCategory(AddCategory addCategory);

    PaginationResponse<CategoryResponse> getAllcategories(int page, int size, boolean ord);

    CategoryResponse getCategory(String categoryName);


}
