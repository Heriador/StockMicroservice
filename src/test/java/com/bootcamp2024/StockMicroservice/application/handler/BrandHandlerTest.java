package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.IBrandRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.IBrandResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BrandHandlerTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @Test
    @DisplayName("Calling method createBrand should pass")
    void createBrandShouldPass(){
        AddBrand addBrand = AddBrand.builder().name("LG").description("marca de televisores").build();
        Brand brand = new Brand(null, "LG", "marca de televisores");

        when(brandRequestMapper.addBrandToBrand(addBrand)).thenReturn(brand);

        doNothing().when(brandServicePort).saveBrand(brand);

        brandHandler.createBrand(addBrand);

        verify(brandRequestMapper, times(1)).addBrandToBrand(addBrand);
        verify(brandServicePort, times(1)).saveBrand(brand);

    }

    @Test
    @DisplayName("Calling method findByName should pass and the return the same object send in the mock")
    void findByNameShouldPass(){
        Brand brand = new Brand(null, "LG", "marca de televisores");
        BrandResponse brandResponse = BrandResponse.builder().name("LG").description("marca de televisores").build();

        when(brandServicePort.findByName("LG")).thenReturn(brand);
        when(brandResponseMapper.toResponse(brand)).thenReturn(brandResponse);

        BrandResponse result = brandHandler.findByName(brand.getName());

        verify(brandServicePort, times(1)).findByName("LG");
        verify(brandResponseMapper, times(1)).toResponse(brand);

        assertEquals(brandResponse, result);

    }

    @Test
    @DisplayName("Calling method findById should pass and the return the same object send in the mock")
    void findByIdShouldPass(){
        Brand brand = new Brand(null, "LG", "marca de televisores");
        BrandResponse brandResponse = BrandResponse.builder().name("LG").description("marca de televisores").build();

        when(brandServicePort.findById(1L)).thenReturn(brand);
        when(brandResponseMapper.toResponse(brand)).thenReturn(brandResponse);

        BrandResponse result = brandHandler.findById(1L);

        verify(brandServicePort, times(1)).findById(1L);
        verify(brandResponseMapper, times(1)).toResponse(brand);

        assertEquals(brandResponse, result);

    }


}