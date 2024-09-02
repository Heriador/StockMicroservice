package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.api.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.CategoryPaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryNotFoundException;


public class CategoryUseCases implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCases(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if(category.getName().trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if(category.getDescription().trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if(categoryPersistencePort.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException("Category already exists");
        }

        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public CategoryPaginationCustom getAllCategories(int page, int size, boolean ord) {
        return categoryPersistencePort.getAllCategories(page, size, ord);
    }

    @Override
    public Category findByName(String categoryName) {
        return categoryPersistencePort.findByName(categoryName).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryPersistencePort.getCategory(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
}
