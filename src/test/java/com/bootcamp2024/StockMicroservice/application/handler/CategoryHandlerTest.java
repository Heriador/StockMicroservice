package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.CategoryResponseMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.GetAllCategoriesMapper;
import com.bootcamp2024.StockMicroservice.domain.api.ICategoryServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
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

    private final GetAllCategoriesMapper getAllCategoriesMapper = Mockito.mock(GetAllCategoriesMapper.class);

    @Autowired
    private final CategoryHandler categoryHandler = new CategoryHandler(categoryServicePort, categoryRequestMapper, categoryResponseMapper, getAllCategoriesMapper);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategory() {
        AddCategory addCategory = AddCategory.builder().name("computadoras").description("para entrar a internet").build();
        Category category = new Category(null,"computadoras", "para entrar a internet");
        when(categoryRequestMapper.addCategoryToCategory(Mockito.any(AddCategory.class))).thenReturn(category);
        doNothing().when(categoryServicePort).saveCategory(category);

        categoryHandler.createCategory(addCategory);

//        verify(categoryRequestMapper, times(1)).addCategoryToCategory(addCategory);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }

//    @Test
//    void getAllCategories() {
//        List<Category> categories = Arrays.asList(new Category(), new Category());
//        List<CategoryResponse> categoryResponses = Arrays.asList(new CategoryResponse(), new CategoryResponse());
//        when(categoryServicePort.getAllCategories()).thenReturn(categories);
//        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);
//
//        List<CategoryResponse> result = categoryHandler.getAllcategories();
//
//        assertEquals(2, result.size());
//        verify(categoryServicePort, times(1)).getAllCategories();
//        verify(categoryResponseMapper, times(1)).toResponseList(categories);
//    }

    @Test
    void getCategory() {
        Category category = new Category(1L,"computadoras","para entrar a internet");
        CategoryResponse categoryResponse = CategoryResponse.builder().id(1L).name("computadoras").description("para entrar a internet").build();

        when(categoryServicePort.getCategory("computadoras")).thenReturn(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoryHandler.getCategory("computadoras");

        assertEquals(categoryResponse, result);
        verify(categoryServicePort, times(1)).getCategory("computadoras");
        verify(categoryResponseMapper, times(1)).toResponse(category);
    }
}