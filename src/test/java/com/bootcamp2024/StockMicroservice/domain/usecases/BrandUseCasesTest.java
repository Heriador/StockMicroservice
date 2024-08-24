package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.BrandAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.BrandNotFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class BrandUseCasesTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCases brandUseCases;

    Brand brand;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        brand = new Brand();
        brand.setId(null);
        brand.setName("LG");
        brand.setDescription("marca de televisores");
    }

    @Test
    @DisplayName("Calling useCase saveBrand should Pass")
    void saveBrandShouldPass(){

        doNothing().when(brandPersistencePort).saveBrand(brand);

        brandUseCases.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);

    }

    @Test
    @DisplayName("Calling useCase saveBrand should throw EmptyFieldExcepcion in Description")
    void saveCategoryShouldThrowDescriptionEmptyFieldException(){
        Brand brand1 = new Brand();

        brand1.setId(null);
        brand1.setName("LG");
        brand1.setDescription("");

        doThrow(EmptyFieldException.class).when(brandPersistencePort).saveBrand(brand1);
        assertThrows(EmptyFieldException.class, ()-> brandUseCases.saveBrand(brand1));
    }

    @Test
    @DisplayName("Calling useCase saveBrand should throw BrandAlreadyExistsException")
    void saveBrandShouldThrowBrandAlreadyExistException(){



        doThrow(BrandAlreadyExistsException.class).when(brandPersistencePort).saveBrand(brand);
        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCases.saveBrand(brand));

    }

    @Test
    @DisplayName("Calling useCase getBrand should pass and return the same object send in the mock")
    void getBrandShouldPass(){

        when(brandPersistencePort.getBrand("LG")).thenReturn(brand);

        Brand brand1 = brandUseCases.getBrand("LG");

        assertEquals(brand, brand1);
        verify(brandPersistencePort, times(1)).getBrand("LG");

    }

    @Test()
    @DisplayName("calling useCase getBrand Should Throw BandNotFoundException")
    void  getBrandShouldThrowBrandNotFoundException(){
        doThrow(BrandNotFoundException.class).when(brandPersistencePort).getBrand("LG");

        assertThrows(BrandNotFoundException.class, ()->brandUseCases.getBrand("LG"));
    }


    @Test
    @DisplayName("Calling useCase getAllBrands should pass and return a pagination response object")
    void getAllBrandsShouldPass(){
        brandUseCases.getAllaBrands(0,10,true);

        when(brandPersistencePort.getAllBrands(0,10,true)).thenReturn(Mockito.mock(BrandPaginationCustom.class));

        verify(brandPersistencePort, times(1)).getAllBrands(0,10,true);

    }

    @Test
    @DisplayName("Calling useCase getAllBrands Should return EmptyList and Throw NoDataFoundException")
    void getAllCategoriesShouldReturnEmptyList() {
        doThrow(NoDataFoundException.class).when(brandPersistencePort).getAllBrands(0, 10,false);

        assertThrows(NoDataFoundException.class, () -> brandPersistencePort.getAllBrands(0, 10,false));
    }


}