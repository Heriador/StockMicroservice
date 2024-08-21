package com.bootcamp2024.StockMicroservice.domain;

import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);

    PaginationCustom getAllCategories(int page, int size);

    Category getCategory(String categoryName);


}
