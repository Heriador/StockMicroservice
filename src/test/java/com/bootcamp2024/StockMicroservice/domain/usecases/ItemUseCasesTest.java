package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.exception.ItemAlreadyExistException;
import com.bootcamp2024.StockMicroservice.domain.exception.ItemNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IItemPersistencePort;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ItemUseCasesTest {

    @Mock
    private IItemPersistencePort itemPersistencePort;

    @InjectMocks
    private ItemUseCases itemUseCases;

    @Autowired
    private static Item item;

    @Autowired
    private static PaginationCustom<Item> paginationCustom;

    @BeforeAll
    static void beforeAll() {
        item = new Item();
        item.setId(1L);
        item.setName("manzana pinto");
        item.setDescription("manzana pintosa");
        item.setPrice(BigDecimal.valueOf(18.9));
        item.setStock(10L);
        item.setBrand(Mockito.mock(Brand.class));
        item.setCategories(List.of(Mockito.mock(Category.class), Mockito.mock(Category.class)));

        paginationCustom = new PaginationCustom<>(List.of(item), 0, 1, 1L, 1, true);
    }

    @Test
    @DisplayName("Calling useCase saveItem should Pass")
    void saveItemShouldPass(){

        doNothing().when(itemPersistencePort).saveItem(item);

        itemUseCases.saveItem(item);

        verify(itemPersistencePort, times(1)).saveItem(item);

    }

    @Test
    @DisplayName("Calling useCase saveItem should throw ItemAlreadyExistsException")
    void saveItemShouldThrowItemAlreadyExistsException(){

        when(itemPersistencePort.findByName(item.getName())).thenReturn(Optional.of(item));

        assertThrows(ItemAlreadyExistException.class, () -> itemUseCases.saveItem(item));

    }

    @Test
    @DisplayName("Calling useCase findByName should Pass and return the same object send in the mock")
    void findByNameShouldPass(){

            when(itemPersistencePort.findByName(item.getName())).thenReturn(Optional.of(item));

            Item itemFound = itemUseCases.findByName(item.getName());

            assertEquals(item, itemFound);
    }

    @Test
    @DisplayName("Calling useCase findByName should throw ItemNotFoundException")
    void findByNameShouldThrowItemNotFoundException(){

        when(itemPersistencePort.findByName(item.getName())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, ()->itemUseCases.findByName("manzana pinto"));

    }

    @Test
    @DisplayName("Calling useCase findById should Pass and return the same object send in the mock")
    void findByIdShouldPass(){

            when(itemPersistencePort.findById(1L)).thenReturn(Optional.of(item));

            Item itemFound = itemUseCases.findById(1L);

            assertEquals(item, itemFound);
    }

    @Test
    @DisplayName("Calling useCase findById should throw ItemNotFoundException")
    void findByIdShouldThrowItemNotFoundException(){

        when(itemPersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemUseCases.findById(1L));

    }

    @Test
    @DisplayName("Calling useCase getAllItems should Pass and return a pagination response object")
    void getAllItemsShouldPass(){

            when(itemPersistencePort.getAllItems(0, 10, "name", true)).thenReturn(Optional.of(paginationCustom));

            PaginationCustom<Item> result = itemUseCases.getAllItems(0, 10, "name", true);

            assertEquals(paginationCustom, result);
    }

    @Test
    @DisplayName("Calling useCase getAllItems should return emptyList and throw NoDataFoundException")
    void getAllItemsShouldThrowNoDataFoundException(){

        when(itemPersistencePort.getAllItems(0, 10, "name", true)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> itemUseCases.getAllItems(0, 10, "name", true));

    }

}