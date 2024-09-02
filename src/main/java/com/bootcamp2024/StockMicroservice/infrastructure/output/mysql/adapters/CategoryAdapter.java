package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;


import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
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
    public Optional<Category> findByName(String categoryName) {

        Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(categoryName);

        return categoryEntity.map(categoryEntityMapper::categoryEntityToCategory);

    }

    @Override
    public Optional<Category> getCategory(Long categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);

        return categoryEntity.map(categoryEntityMapper::categoryEntityToCategory);

    }
}
