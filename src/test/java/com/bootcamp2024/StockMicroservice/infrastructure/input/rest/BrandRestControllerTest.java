package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.handler.IBrandHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BrandRestControllerTest {

    @Mock
    private IBrandHandler brandHandler;

    @InjectMocks
    private BrandRestController brandRestController;

    @Autowired
    private static AddBrand addBrand;

    private static BrandResponse brandResponse;

    @BeforeAll
    static void beforeAll() {

        addBrand = new AddBrand();
        addBrand.setName("LG");
        addBrand.setDescription("Marca de televisores");

        brandResponse = new BrandResponse();
        brandResponse.setId(1L);
        brandResponse.setName(addBrand.getName());
        brandResponse.setDescription(addBrand.getDescription());

    }

    @Test
    @DisplayName("Calling method createBrand shouldPass")
    void createBrandShouldPass(){

        doNothing().when(brandHandler).createBrand(addBrand);

        ResponseEntity<Void> response = brandRestController.createBrand(addBrand);

        verify(brandHandler, times(1)).createBrand(addBrand);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

    }

    @Test
    @DisplayName("Calling method getCategoryByName should pass and return the same object send in the mock")
    void getCategoryByNameShouldPass(){

        when(brandHandler.findByName("LG")).thenReturn(brandResponse);

        ResponseEntity<BrandResponse> response = brandRestController.getBrandByName("LG");

        verify(brandHandler, times(1)).findByName("LG");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandResponse, response.getBody());

    }

    @Test
    @DisplayName("Calling method getCategoryByName should pass and return the same object send in the mock")
    void getCategoryByIdShouldPass(){

        when(brandHandler.findById(1L)).thenReturn(brandResponse);

        ResponseEntity<BrandResponse> response = brandRestController.getBrandById(1L);

        verify(brandHandler, times(1)).findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandResponse, response.getBody());

    }
}