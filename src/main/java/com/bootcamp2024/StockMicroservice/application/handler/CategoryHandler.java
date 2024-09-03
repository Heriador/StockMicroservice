package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryResponseMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.PaginationResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.CategoryPaginationCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final PaginationResponseMapper paginationResponseMapper;

    @Override
    public void createCategory(AddCategory addCategory) {
        Category category = categoryRequestMapper.addCategoryToCategory(addCategory);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public PaginationResponse<CategoryResponse> getAllcategories(int page, int size, boolean ord) {

        PaginationCustom<Category> paginationCustom = categoryServicePort.getAllCategories(page, size, ord);


        return paginationResponseMapper.paginationCustomToGetAllCategories(paginationCustom);
    }

    @Override
    public CategoryResponse getCategory(String categoryName) {
        Category category = categoryServicePort.findByName(categoryName);
        return categoryResponseMapper.toResponse(category);
    }

}
