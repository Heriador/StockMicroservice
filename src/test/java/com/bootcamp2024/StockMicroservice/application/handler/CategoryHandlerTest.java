package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryResponseMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.PaginationResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryHandlerTest {

    private final ICategoryServicePort categoryServicePort = Mockito.mock(ICategoryServicePort.class);

    private final CategoryRequestMapper categoryRequestMapper = Mockito.mock(CategoryRequestMapper.class);

    private final CategoryResponseMapper categoryResponseMapper = Mockito.mock(CategoryResponseMapper.class);

    private final PaginationResponseMapper paginationResponseMapper = Mockito.mock(PaginationResponseMapper.class);

    @Autowired
    private final CategoryHandler categoryHandler = new CategoryHandler(categoryServicePort, categoryRequestMapper, categoryResponseMapper, paginationResponseMapper);



    @Test
    void createCategory() {
        AddCategory addCategory = AddCategory.builder().name("computadoras").description("para entrar a internet").build();
        Category category = new Category(null,"computadoras", "para entrar a internet");
        when(categoryRequestMapper.addCategoryToCategory(Mockito.any(AddCategory.class))).thenReturn(category);
        doNothing().when(categoryServicePort).saveCategory(category);

        categoryHandler.createCategory(addCategory);

        verify(categoryServicePort, times(1)).saveCategory(category);
    }

    @Test
    void getCategory() {
        Category category = new Category(1L,"computadoras","para entrar a internet");
        CategoryResponse categoryResponse = CategoryResponse.builder().id(1L).name("computadoras").description("para entrar a internet").build();

        when(categoryServicePort.findByName("computadoras")).thenReturn(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoryHandler.getCategory("computadoras");

        assertEquals(categoryResponse, result);
        verify(categoryServicePort, times(1)).findByName("computadoras");
        verify(categoryResponseMapper, times(1)).toResponse(category);
    }
}