package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql;

import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryNotFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }


        CategoryEntity category1 = categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
        System.out.println(category1);

    }

    @Override
    public List<Category> getAllCategories() {

        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();

        if(categoryEntityList.isEmpty()){
            throw new NoDataFoundException("No categories found");
        }

        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Category getCategory(String categoryName) {

        return categoryEntityMapper.categoryEntityToCategory(categoryRepository.findByName(categoryName)
                .orElseThrow(CategoryNotFoundException::new));
    }
}
