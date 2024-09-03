package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.exception.CategoryNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.spi.ICategoryPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import com.bootcamp2024.StockMicroservice.domain.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
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

    @Autowired
    private static Category category;

    @BeforeAll
    static void beforeAll() {
        category = new Category(null,"computadoras","para entrar a internet");
    }

    @Test
    @DisplayName("Calling useCase saveCategory Should pass")
    void saveCategoryShouldPass() {

        categoryUseCases.saveCategory(category);
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    @DisplayName("Calling useCase saveCategory should throw EmptyFieldException For field name")
    void  saveCategoryShouldThrowNameEmptyFieldException(){
        Category categoryEmptyName = new Category(null, "", "para entrar a internet");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ()->categoryUseCases.saveCategory(categoryEmptyName));

        assertEquals(DomainConstants.Field.NAME.toString(),exception.getMessage());

    }

    @Test
    @DisplayName("Calling useCase saveCategory should throw EmptyFieldException For field description")
    void  saveCategoryShouldThrowDescriptionEmptyFieldException(){
        Category categoryEmptyDescription = new Category(null, "computadores", "");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ()->categoryUseCases.saveCategory(categoryEmptyDescription));

        assertEquals(DomainConstants.Field.DESCRIPTION.toString(),exception.getMessage());

    }

    @Test
    @DisplayName("Calling useCase saveCategory should throw CategoryAlreadyExistsException")
    void saveCategoryShouldThrowCategoryAlreadyExistsException() {


        when(categoryPersistencePort.findByName(category.getName())).thenReturn(Optional.of(category));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCases.saveCategory(category));
    }

    @Test
    @DisplayName("Calling useCase getCategory should pass and return the same object send in the mock")
    void getCategory() {

        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.of(category));

        Category result = categoryUseCases.getCategory(1L);

        verify(categoryPersistencePort, times(1)).getCategory(1L);
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Calling useCase findById should throw CategoryNotFoundException")
    void getCategoryShouldThrowCategoryNotFoundException(){

        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCases.getCategory(1L));

    }

    @Test
    @DisplayName("Calling useCase findByName should pass and return the same object send in the mock")
    void findByName() {

        when(categoryPersistencePort.findByName("computadoras")).thenReturn(Optional.of(category));

        Category result = categoryUseCases.findByName("computadoras");

        verify(categoryPersistencePort, times(1)).findByName("computadoras");
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Calling useCase findById should throw CategoryNotFoundException")
    void findByNameShouldThrowCategoryNotFoundException(){

        when(categoryPersistencePort.findByName("computadoras")).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryUseCases.findByName("computadoras"));

    }

    @Test
    @DisplayName("Calling useCase getAllCategories should pass and return the same paginationCustomObject send in the mock")
    void getAllCategories() {
        PaginationCustom<Category> paginationCustom = new PaginationCustom<Category>();

        paginationCustom.setContent(List.of(category));
        paginationCustom.setPageNumber(0);
        paginationCustom.setPageSize(1);
        paginationCustom.setTotalElements(1L);
        paginationCustom.setTotalPages(1);
        paginationCustom.setLast(true);

        when(categoryPersistencePort.getAllCategories(0, 10,true)).thenReturn(Optional.of(paginationCustom));

        PaginationCustom<Category> result = categoryUseCases.getAllCategories(0, 10,true);


        verify(categoryPersistencePort, times(1)).getAllCategories(0, 10, true);
        assertEquals(paginationCustom,result);
    }

    @Test
    @DisplayName("Calling useCase getAllCategories should throw NoDataFoundException")
    void getAllCategoriesShouldReturnEmptyList() {
        when(categoryPersistencePort.getAllCategories(0, 10,true)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> categoryUseCases.getAllCategories(0, 10, true));
    }
}