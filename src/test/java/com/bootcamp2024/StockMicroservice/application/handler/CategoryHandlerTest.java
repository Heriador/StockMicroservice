package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.AddCategory;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.CategoryAdapter;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryHandlerTest {

    @Mock
    private ICategoryRepository categoryRepository;
    @Autowired
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    @Autowired
    private CategoryAdapter categoryAdapter;

    @Autowired
    private ICategoryServicePort categoryServicePort;
    @Autowired
    private CategoryRequestMapper categoryRequestMapper;
    @Autowired
    private CategoryResponseMapper categoryResponseMapper;

    @Autowired
    private ICategoryHandler categoryHandler;

    @Test
    void CategoryHandler_CreateCategory_ReturnNothing(){
        AddCategory addCategory = AddCategory.builder().name("computadoras").description("para entrar a internet").build();
        CategoryEntity categoryEntity = CategoryEntity.builder().id(1L).name("computadoras").description("para entrar a internet").build();

        when(categoryRepository.save(Mockito.any(CategoryEntity.class))).thenReturn(categoryEntity);

        categoryHandler.createCategory(addCategory);


        verify(categoryHandler).createCategory(addCategory);



    }

}