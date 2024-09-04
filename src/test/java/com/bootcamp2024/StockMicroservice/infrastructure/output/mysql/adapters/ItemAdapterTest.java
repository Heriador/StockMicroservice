package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IItemEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.BrandEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ItemAdapterTest {

    @Mock
    private IItemRepository itemRepository;

    @Mock
    private IItemEntityMapper itemEntityMapper;

    @InjectMocks
    private ItemAdapter itemAdapter;

    @Autowired
    private static Item item;

    @Autowired
    private static ItemEntity itemEntity;


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

        itemEntity = new ItemEntity();
        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setStock(item.getStock());
        itemEntity.setBrand(Mockito.mock(BrandEntity.class));
        itemEntity.setCategories(List.of(Mockito.mock(CategoryEntity.class), Mockito.mock(CategoryEntity.class)));

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




}