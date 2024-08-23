package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.BrandAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    void saveBrandShouldPass(){

        doNothing().when(brandPersistencePort).saveBrand(brand);

        brandUseCases.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);

    }

    @Test
    void saveBrandShouldThrowBrandAlreadyExistException(){
        Brand brand1 = new Brand();

        brand1.setId(null);
        brand1.setName("LG");
        brand1.setDescription("");


        doThrow(BrandAlreadyExistsException.class).when(brandPersistencePort).saveBrand(brand);
        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCases.saveBrand(brand));

    }

    @Test
    void getBrandShouldPass(){

        when(brandPersistencePort.getBrand("LG")).thenReturn(brand);

        Brand brand1 = brandUseCases.getBrand("LG");

        assertEquals(brand, brand1);
        verify(brandPersistencePort, times(1)).getBrand("LG");

    }


}