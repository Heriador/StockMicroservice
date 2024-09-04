package com.bootcamp2024.StockMicroservice.infrastructure.input.rest;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.handler.ItemHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class ItemRestControllerTest {

    @Mock
    private ItemHandler itemHandler;

    @InjectMocks
    private ItemRestController itemRestController;

    @Autowired
    private static AddItem addItem;

    private static ItemResponse itemResponse;

    @BeforeAll
    static void beforeAll() {

            addItem = new AddItem();
            addItem.setName("manzana pinto");
            addItem.setDescription("manzana pintosa");
            addItem.setPrice(BigDecimal.valueOf(18.9));
            addItem.setStock(10L);
            addItem.setBrandId(1L);
            addItem.setCategories(List.of(1L, 2L));

            itemResponse = new ItemResponse();
            itemResponse.setId(1L);
            itemResponse.setName(addItem.getName());
            itemResponse.setDescription(addItem.getDescription());
            itemResponse.setPrice(addItem.getPrice());
            itemResponse.setStock(addItem.getStock());
            itemResponse.setBrand(Mockito.mock(BrandResponse.class));
            itemResponse.setCategories(List.of(Mockito.mock(CategoryResponse.class), Mockito.mock(CategoryResponse.class)));
    }

    @Test
    @DisplayName("Calling method createItem shouldPass")
    void createItemShouldPass(){

        doNothing().when(itemHandler).createItem(addItem);

        ResponseEntity<Void> response = itemRestController.createItem(addItem);

        verify(itemHandler, times(1)).createItem(addItem);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

    }

    @Test
    @DisplayName("Calling method getItemByName should pass and return the same object send in the mock")
    void getItemByNameShouldPass(){

        when(itemHandler.findByName("manzana pinto")).thenReturn(itemResponse);

        ResponseEntity<ItemResponse> response = itemRestController.getItemByName("manzana pinto");

        verify(itemHandler, times(1)).findByName("manzana pinto");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemResponse, response.getBody());
    }

    @Test
    @DisplayName("Calling method getItemById should pass and return the same object send in the mock")
    void getItemByIdShouldPass(){

        when(itemHandler.findById(1L)).thenReturn(itemResponse);

        ResponseEntity<ItemResponse> response = itemRestController.getItemById(1L);

        verify(itemHandler, times(1)).findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemResponse, response.getBody());
    }

}