package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    void saveCategoryShouldPass() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        categoryUseCases.saveCategory(category);
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void saveCategoryShouldThrowCategoryAlreadyExistsException() {
        Category category = new Category(null, "computadoras", "para entrar a internet");
        doThrow(CategoryAlreadyExistsException.class).when(categoryPersistencePort).saveCategory(category);
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCases.saveCategory(category));
    }

    @Test
    void getCategory() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        when(categoryPersistencePort.getCategory("computadoras")).thenReturn(category);

        Category result = categoryUseCases.getCategory("computadoras");
        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).getCategory("computadoras");
    }

    @Test
    void getAllCategories() {
        categoryUseCases.getAllCategories(0, 10);

        when(categoryPersistencePort.getAllCategories(0, 10)).thenReturn(Mockito.mock(PaginationCustom.class));

        verify(categoryPersistencePort, times(1)).getAllCategories(0, 10);
    }

    @Test
    void getAllCategoriesShouldReturnEmptyList() {
        doThrow(NoDataFoundException.class).when(categoryPersistencePort).getAllCategories(0, 10);

        assertThrows(NoDataFoundException.class, () -> categoryUseCases.getAllCategories(0, 10));
    }
}