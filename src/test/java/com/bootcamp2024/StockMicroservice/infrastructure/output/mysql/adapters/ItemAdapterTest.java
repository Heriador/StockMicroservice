package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.Factory.ItemFactory;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IItemEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.PaginationMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.ItemEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IItemRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ItemAdapterTest {

    @Mock
    private IItemRepository itemRepository;

    @Mock
    private IItemEntityMapper itemEntityMapper;

    @Mock
    private PaginationMapper paginationMapper;

    @InjectMocks
    private ItemAdapter itemAdapter;

    @Autowired
    private static Item item;

    @Autowired
    private static ItemEntity itemEntity;

    private static PaginationCustom<Item> paginationCustom;


    @BeforeAll
    static void beforeAll() {
        item = ItemFactory.getItem();
        itemEntity = ItemFactory.getItemEntity();

        paginationCustom = ItemFactory.getPaginationCustom();
    }


    @Test
    @DisplayName("Calling method saveItem should pass")
    void saveItemShouldPass(){

            when(itemEntityMapper.itemToItemEntity(item)).thenReturn(itemEntity);
            when(itemRepository.save(itemEntity)).thenReturn(itemEntity);

            itemAdapter.saveItem(item);

            verify(itemEntityMapper, times(1)).itemToItemEntity(item);
            verify(itemRepository, times(1)).save(itemEntity);
    }

    @Test
    @DisplayName("Calling method findByName should pass and return the same object send in the mock")
    void findByNameShouldPass(){
        when(itemRepository.findByName(item.getName())).thenReturn(Optional.of(itemEntity));
        when(itemEntityMapper.itemEntityToItem(itemEntity)).thenReturn(item);

        Optional<Item> response = itemAdapter.findByName(item.getName());

        assertEquals(Optional.of(item), response);
    }

    @Test
    @DisplayName("Calling method findById should pass and return the same object send in the mock")
    void findByIdShouldPass(){
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(itemEntity));
        when(itemEntityMapper.itemEntityToItem(itemEntity)).thenReturn(item);

        Optional<Item> response = itemAdapter.findById(item.getId());

        assertEquals(Optional.of(item), response);
    }

    @Test
    @DisplayName("Calling method getAllItems should return the same object send in the mock")
    void getAllItemsShouldPass(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("name").ascending());
        Page<ItemEntity> itemEntityPage = Mockito.mock(Page.class);

        when(itemRepository.findAll(pageable)).thenReturn(itemEntityPage);
        when(paginationMapper.toItemPaginationCustom(itemEntityPage)).thenReturn(paginationCustom);

        Optional<PaginationCustom<Item>> result = itemAdapter.getAllItems(0, 10, "name", true);

        verify(itemRepository, times(1)).findAll(pageable);
        verify(paginationMapper, times(1)).toItemPaginationCustom(itemEntityPage);

        assertEquals(Optional.of(paginationCustom), result);
    }

    @Test
    @DisplayName("Calling method addStock should pass")
    void addStockShouldPass(){

            int quantity = 10;

            item.setStock(item.getStock() + quantity);

            when(itemEntityMapper.itemToItemEntity(item)).thenReturn(itemEntity);
            when(itemRepository.save(itemEntityMapper.itemToItemEntity(item))).thenReturn(itemEntity);

            itemAdapter.addStock(item, quantity);

            verify(itemRepository, times(1)).save(itemEntityMapper.itemToItemEntity(item));
    }


}