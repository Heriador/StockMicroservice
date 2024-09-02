package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    static Category category;

    static CategoryEntity categoryEntity;

    @BeforeAll
    static void beforeAll() {
       category = new Category(null,"computadoras","para entrar a internet");
       categoryEntity = new CategoryEntity();
       categoryEntity.setName("computadoras");
       categoryEntity.setDescription("para entrar a internet");
    }

    @BeforeEach
    void setUp() {
        when(categoryEntityMapper.categoryToCategoryEntity(category)).thenReturn(categoryEntity);
    }

    @Test
    void saveCategoryShouldPass() {

        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        categoryAdapter.saveCategory(category);

        Mockito.verify(categoryRepository, times(1)).save(categoryEntity);

    }

    @Test
    void getCategoryByNameShouldPass(){

        Optional<CategoryEntity> categoryEntityOptional = Optional.of(categoryEntityMapper.categoryToCategoryEntity(category));

        when(categoryRepository.findByName("computadoras")).thenReturn(categoryEntityOptional);
        when(categoryEntityMapper.categoryEntityToCategory(categoryEntity)).thenReturn(category);


        Category response = categoryAdapter.findByName("computadoras").get();


        assertEquals(category,response);
    }

    @Test
    void getCategoryByIdShouldPass(){
        Optional<CategoryEntity> categoryEntityOptional = Optional.of(categoryEntityMapper.categoryToCategoryEntity(category));

        when(categoryRepository.findById(1L)).thenReturn(categoryEntityOptional);
        when(categoryEntityMapper.categoryEntityToCategory(categoryEntity)).thenReturn(category);


        Category response = categoryAdapter.getCategory(1L).get();


        assertEquals(category,response);
    }

}