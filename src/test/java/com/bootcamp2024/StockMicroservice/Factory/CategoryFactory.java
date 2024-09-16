package com.bootcamp2024.StockMicroservice.Factory;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;

public class CategoryFactory {

    private static final Category category;
    private static final CategoryEntity categoryEntity;
    private static final AddCategory addCategory;
    private static final CategoryResponse categoryResponse;



    static {
        category = new Category(1L, "category", "description");

        categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());

        addCategory = new AddCategory();
        addCategory.setName(category.getName());
        addCategory.setDescription(category.getDescription());

        categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
    }

    public static Category getCategory(){
        return category;
    }

    public static CategoryEntity getCategoryEntity(){
        return categoryEntity;
    }

    public static AddCategory getAddCategory(){
        return addCategory;
    }

    public static CategoryResponse getCategoryResponse(){
        return categoryResponse;
    }

}
