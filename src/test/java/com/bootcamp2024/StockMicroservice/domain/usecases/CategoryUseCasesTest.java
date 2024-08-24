package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.CategoryPaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Calling useCase saveCategory Should pass")
    void saveCategoryShouldPass() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        categoryUseCases.saveCategory(category);
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    @DisplayName("Calling useCase saveCategory should throw CategoryAlreadyExistsException")
    void saveCategoryShouldThrowCategoryAlreadyExistsException() {
        Category category = new Category(null, "computadoras", "para entrar a internet");
        doThrow(CategoryAlreadyExistsException.class).when(categoryPersistencePort).saveCategory(category);
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCases.saveCategory(category));
    }

    @Test
    @DisplayName("Calling useCase getCategory Should pass and return the same object send in the mock")
    void getCategory() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        when(categoryPersistencePort.getCategory("computadoras")).thenReturn(category);

        Category result = categoryUseCases.getCategory("computadoras");
        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).getCategory("computadoras");
    }

    @Test
    @DisplayName("Calling useCase getAllCategories Should pass and return a paginationCustom Object")
    void getAllCategoriesShouldPass() {
        categoryUseCases.getAllCategories(0, 10,true);

        when(categoryPersistencePort.getAllCategories(0, 10,true)).thenReturn(Mockito.mock(CategoryPaginationCustom.class));

        verify(categoryPersistencePort, times(1)).getAllCategories(0, 10,true);
    }

    @Test
    @DisplayName("Calling useCase getAllCategories Should return EmptyList and Throw NoDataFoundException")
    void getAllCategoriesShouldReturnEmptyList() {
        doThrow(NoDataFoundException.class).when(categoryPersistencePort).getAllCategories(0, 10,false);

        assertThrows(NoDataFoundException.class, () -> categoryUseCases.getAllCategories(0, 10,false));
    }
}