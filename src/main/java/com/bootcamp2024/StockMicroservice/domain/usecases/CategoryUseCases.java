package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;

import java.util.List;

public class CategoryUseCases implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCases(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public Category getCategory(String categoryName) {
        return categoryPersistencePort.getCategory(categoryName);
    }

}
