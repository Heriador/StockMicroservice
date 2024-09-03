package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddCategory;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.handler.ICategoryHandler;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


class CategoryRestControllerTest {

    ICategoryHandler categoryHandler = Mockito.mock(ICategoryHandler.class);

    @Autowired
    CategoryResponse categoryResponseMock;

    @Autowired
    AddCategory addCategory;

    @Autowired
    CategoryRestController categoryRestController = new CategoryRestController(categoryHandler);


    @BeforeEach
    void setup(){
        CategoryResponse category = CategoryResponse.builder()
                                        .id(1L)
                                        .name("computadoras")
                                        .description("para entrar a internet")
                                        .build();
        addCategory = AddCategory.builder()
                                    .name(category.getName())
                                    .description(category.getDescription())
                                    .build();

        Mockito.when(categoryHandler.getCategory("computadoras")).thenReturn(category);

    }

    @Test
    @DisplayName("Save values should return nothing")
    void saveCategory(){
        Mockito.doNothing().when(categoryHandler).createCategory(addCategory);

        ResponseEntity<Void> response = categoryRestController.createCategory(addCategory);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        Mockito.verify(categoryHandler).createCategory(addCategory);

    }

    @Test
    void saveCategoryShouldFailed() throws CategoryAlreadyExistsException {
        Mockito.doThrow(CategoryAlreadyExistsException.class).when(categoryHandler).createCategory(addCategory);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryRestController.createCategory(addCategory));
    }

    @Test
    void getCategory(){

        categoryResponseMock = categoryRestController.getCategory("computadoras").getBody();
        System.out.println(categoryResponseMock);
        assertEquals("computadoras",categoryResponseMock.getName());
    }

    @Test
    void getCategoryShouldFailed() throws CategoryNotFoundException{
        Mockito.doThrow(CategoryNotFoundException.class).when(categoryHandler).getCategory("computadoras");

        assertThrows(CategoryNotFoundException.class, () -> categoryRestController.getCategory("computadoras"));
    }






}