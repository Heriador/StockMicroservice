package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.CategoryPaginationCustom;


public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    CategoryPaginationCustom getAllCategories(int page, int size, boolean ord);

    Category getCategory(String categoryName);


}
