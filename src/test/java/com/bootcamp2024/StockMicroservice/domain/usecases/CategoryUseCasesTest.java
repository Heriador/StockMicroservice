package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


class CategoryUseCasesTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCases categoryUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        categoryUseCases.saveCategory(category);
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void getCategory() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        when(categoryPersistencePort.getCategory("computadoras")).thenReturn(category);

        Category result = categoryUseCases.getCategory("computadoras");
        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).getCategory("computadoras");
    }
}