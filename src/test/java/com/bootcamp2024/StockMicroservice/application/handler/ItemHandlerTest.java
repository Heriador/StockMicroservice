package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ItemHandlerTest {

    @Mock
    private IItemServicePort itemServicePort;

    @Mock
    private IItemResponseMapper itemResponseMapper;

    @Mock
    private IItemRequestMapper itemRequestMapper;


    @InjectMocks
    private ItemHandler itemHandler;

    @Autowired
    private static AddItem addItem;

    @Autowired
    private static Item item;

    @Autowired
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

        item = new Item(1L, "manzana pinto", "manzana pintosa", BigDecimal.valueOf(18.9), 10L, Mockito.mock(Brand.class), List.of(Mockito.mock(Category.class), Mockito.mock(Category.class)));

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
    @DisplayName("Calling method createItem should pass")
    void createItemShouldPass(){
        when(itemRequestMapper.toItem(addItem)).thenReturn(item);
        doNothing().when(itemServicePort).saveItem(item);

        itemHandler.createItem(addItem);

        verify(itemRequestMapper, times(1)).toItem(addItem);
        verify(itemServicePort, times(1)).saveItem(item);
    }

    @Test
    @DisplayName("Calling method findByName should pass and then return the same object send in the mock")
    void findByNameShouldPass(){

        when(itemServicePort.findByName("manzana pinto")).thenReturn(item);
        when(itemResponseMapper.toItemResponse(item)).thenReturn(itemResponse);

        ItemResponse result = itemHandler.findByName("manzana pinto");

        verify(itemServicePort, times(1)).findByName("manzana pinto");
        verify(itemResponseMapper, times(1)).toItemResponse(item);

        assertEquals(itemResponse, result);

    }

    @Test
    @DisplayName("Calling method findById should pass and then return the same object send in the mock")
    void findByIdShouldPass(){

        when(itemServicePort.findById(1L)).thenReturn(item);
        when(itemResponseMapper.toItemResponse(item)).thenReturn(itemResponse);

        ItemResponse result = itemHandler.findById(1L);

        verify(itemServicePort, times(1)).findById(1L);
        verify(itemResponseMapper, times(1)).toItemResponse(item);

        assertEquals(itemResponse, result);

    }

}