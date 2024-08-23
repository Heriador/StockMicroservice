package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.GetAllCategories;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryResponseMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.GetAllCategoriesMapper;
import com.bootcamp2024.StockMicroservice.domain.api.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
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
    private final GetAllCategoriesMapper getAllCategoriesMapper;

    @Override
    public void createCategory(AddCategory addCategory) {
        Category category = categoryRequestMapper.addCategoryToCategory(addCategory);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public GetAllCategories getAllcategories(int page, int size) {

        PaginationCustom paginationCustom = categoryServicePort.getAllCategories(page, size);


        return getAllCategoriesMapper.paginationCustomToGetAllCategories(paginationCustom);
    }

    @Override
    public CategoryResponse getCategory(String categoryName) {
        Category category = categoryServicePort.getCategory(categoryName);
        return categoryResponseMapper.toResponse(category);
    }

}
