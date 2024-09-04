package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.PaginationMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;



import java.util.Optional;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PaginationMapper paginationMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
    }

    @Override
    public Optional<PaginationCustom<Category>> getAllCategories(int page, int size, boolean ord) {
        Sort sort = ord ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);

        PaginationCustom<Category> categoryPaginationCustom = paginationMapper.toPaginationCustom(categoryEntityPage);

        if(categoryPaginationCustom.getContent().isEmpty()){
            return Optional.empty();
        }

        return Optional.of(categoryPaginationCustom);
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
