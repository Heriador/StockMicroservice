package com.bootcamp2024.StockMicroservice.domain.usecases;


import com.bootcamp2024.StockMicroservice.domain.exception.CategoryNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.DisplayName;

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
    @DisplayName("Calling useCase saveCategory Should pass")
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
    @DisplayName("Calling useCase saveCategory should throw CategoryAlreadyExistsException")
    void saveCategoryShouldThrowCategoryAlreadyExistsException() {
        Category category = new Category(null, "computadoras", "para entrar a internet");

        when(categoryPersistencePort.findByName(category.getName())).thenReturn(Optional.of(category));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCases.saveCategory(category));
    }

    @Test
    @DisplayName("Calling useCase getCategory Should pass and return the same object send in the mock")
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