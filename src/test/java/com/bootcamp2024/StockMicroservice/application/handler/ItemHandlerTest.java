package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.Factory.ItemFactory;
import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.application.dto.request.AddStock;
import com.bootcamp2024.StockMicroservice.application.dto.response.*;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.IItemResponseMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.PaginationResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IItemServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


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

    @Mock
    private PaginationResponseMapper paginationResponseMapper;


    @InjectMocks
    private ItemHandler itemHandler;

    @Autowired
    private static AddItem addItem;

    @Autowired
    private static Item item;

    @Autowired
    private static ItemResponse itemResponse;

    @Autowired
    private static PaginationCustom<Item> paginationCustom;

    @Autowired
    private static PaginationResponse<ItemResponse> paginationResponse;

    @BeforeAll
    static void beforeAll() {

        addItem = ItemFactory.getAddItem();
        item = ItemFactory.getItem();

        itemResponse = ItemFactory.getItemResponse();

        paginationCustom = ItemFactory.getPaginationCustom();

        paginationResponse = ItemFactory.getPaginationResponse();

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

    @Test
    @DisplayName("Calling method getAllItems should pass and return the same object that was send in the mock")
    void getAllItemsShouldPass(){

        when(itemServicePort.getAllItems(0, 10, "name", true)).thenReturn(paginationCustom);
        when(paginationResponseMapper.toItemPaginationResponse(paginationCustom)).thenReturn(paginationResponse);

        PaginationResponse<ItemResponse> result = itemHandler.getAllItems(0, 10, "name", true);

        verify(itemServicePort, times(1)).getAllItems(0, 10, "name", true);
        verify(paginationResponseMapper, times(1)).toItemPaginationResponse(paginationCustom);

        assertEquals(paginationResponse, result);

    }

    @Test
    @DisplayName("Calling method addStock should pass")
    void addStockShouldPass(){

            AddStock addStock = new AddStock();
            addStock.setQuantity(10);

            doNothing().when(itemServicePort).addStock(1L, 10);

            itemHandler.addStock(1L, addStock);

            verify(itemServicePort, times(1)).addStock(1L, 10);
    }


}