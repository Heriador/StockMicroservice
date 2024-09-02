package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.exception.CategoryNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class CategoryUseCasesTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCases categoryUseCases;


    @Test
    void saveCategoryShouldPass() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        categoryUseCases.saveCategory(category);
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void  saveCategoryShouldThrowNameEmptyFieldException(){
        Category category = new Category(null, "", "para entrar a internet");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ()->categoryUseCases.saveCategory(category));

        assertEquals(DomainConstants.Field.NAME.toString(),exception.getMessage());

    }

    @Test
    void  saveCategoryShouldThrowDescriptionEmptyFieldException(){
        Category category = new Category(null, "computadores", "");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ()->categoryUseCases.saveCategory(category));

        assertEquals(DomainConstants.Field.DESCRIPTION.toString(),exception.getMessage());

    }

    @Test
    void saveCategoryShouldThrowCategoryAlreadyExistsException() {
        Category category = new Category(null, "computadoras", "para entrar a internet");

        when(categoryPersistencePort.findByName(category.getName())).thenReturn(Optional.of(category));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCases.saveCategory(category));
    }

    @Test
    void getCategory() {
        Category category = new Category(null,"computadoras","para entrar a internet");
        when(categoryPersistencePort.findByName("computadoras")).thenReturn(Optional.of(category));

        Category result = categoryUseCases.findByName("computadoras");
        assertEquals(category, result);
        verify(categoryPersistencePort, times(1)).findByName("computadoras");
    }

    @Test
    void getCategoryShouldThrowCategoryNotFoundException(){

        when(categoryPersistencePort.findByName("computadoras")).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCases.findByName("computadoras"));

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