package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Category;

import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;

import java.util.Optional;



public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    Optional<PaginationCustom<Category>> getAllCategories(int page, int size, boolean order);

    Optional<Category> findByName(String categoryName);

    Optional<Category> getCategory(Long categoryId);


}
