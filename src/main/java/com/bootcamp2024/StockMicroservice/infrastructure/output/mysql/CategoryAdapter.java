package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql;

import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryNotFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;


import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException("Category already exists");
        }


        CategoryEntity category1 = categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
        System.out.println(category1);

    }

    @Override
    public PaginationCustom getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);

        if(categoryEntityPage.isEmpty()){
            throw new NoDataFoundException("No categories found");
        }

        List<CategoryEntity> categoryEntityList = categoryEntityPage.getContent();

        PaginationCustom pagination = new PaginationCustom();
        pagination.setContent(categoryEntityMapper.toCategoryList(categoryEntityList));
        pagination.setPageNumber(categoryEntityPage.getNumber());
        pagination.setPageSize(categoryEntityPage.getSize());
        pagination.setTotalElements(categoryEntityPage.getTotalElements());
        pagination.setTotalPages(categoryEntityPage.getTotalPages());
        pagination.setLast(categoryEntityPage.isLast());


        return pagination;
    }

    @Override
    public Category getCategory(String categoryName) {

        return categoryEntityMapper.categoryEntityToCategory(categoryRepository.findByName(categoryName)
                .orElseThrow(CategoryNotFoundException::new));
    }
}
