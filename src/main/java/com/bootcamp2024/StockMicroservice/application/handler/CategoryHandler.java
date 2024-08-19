package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public void createCategory(AddCategory addCategory) {
        Category category = categoryRequestMapper.addCategoryToCategory(addCategory);
    }

    @Override
    public List<CategoryResponse> getAllcategories() {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategories());
    }

    @Override
    public CategoryResponse getCategory(String categoryName) {
        Category category = categoryServicePort.getCategory(categoryName);
        return categoryResponseMapper.toResponse(category);
    }

}
