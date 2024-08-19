package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;

import java.util.List;

public class CategoryUseCases implements ICategoryServicePort {

    private final ICategoryPersistencePort categoriePersistencePort;

    public CategoryUseCases(ICategoryPersistencePort categoriePersistencePort) {
        this.categoriePersistencePort = categoriePersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        categoriePersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoriePersistencePort.getAllCategories();
    }

    @Override
    public Category getCategory(String categoryName) {
        return categoriePersistencePort.getCategory(categoryName);
    }

}
