package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.CategoryEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.PaginationMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @Mock
    private PaginationMapper paginationMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    static Category category;

    static CategoryEntity categoryEntity;

    @BeforeAll
    static void beforeAll() {
       category = new Category(null,"computadoras","para entrar a internet");
       categoryEntity = new CategoryEntity();
       categoryEntity.setName("computadoras");
       categoryEntity.setDescription("para entrar a internet");
    }

    @Test
    void saveCategoryShouldPass() {

        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(categoryEntityMapper.categoryToCategoryEntity(category)).thenReturn(categoryEntity);

        categoryAdapter.saveCategory(category);

        Mockito.verify(categoryRepository, times(1)).save(categoryEntity);

    }

    @Test
    void getCategoryByNameShouldPass(){
        when(categoryEntityMapper.categoryToCategoryEntity(category)).thenReturn(categoryEntity);
        Optional<CategoryEntity> categoryEntityOptional = Optional.of(categoryEntityMapper.categoryToCategoryEntity(category));

        when(categoryRepository.findByName("computadoras")).thenReturn(categoryEntityOptional);
        when(categoryEntityMapper.categoryEntityToCategory(categoryEntity)).thenReturn(category);


        Category response = categoryAdapter.findByName("computadoras").get();


        assertEquals(category,response);
    }

    @Test
    void getCategoryByIdShouldPass(){
        when(categoryEntityMapper.categoryToCategoryEntity(category)).thenReturn(categoryEntity);
        Optional<CategoryEntity> categoryEntityOptional = Optional.of(categoryEntityMapper.categoryToCategoryEntity(category));

        when(categoryRepository.findById(1L)).thenReturn(categoryEntityOptional);
        when(categoryEntityMapper.categoryEntityToCategory(categoryEntity)).thenReturn(category);


        Category response = categoryAdapter.getCategory(1L).get();


        assertEquals(category,response);
    }

    @Test
    @DisplayName("Calling method getAllCategories should return an Optional Object of Type Pagination<Category>")
    void getAllCategoriesShouldPass(){

        Page<CategoryEntity> categoryEntityPage = Mockito.mock(Page.class);
        PaginationCustom<Category> categoryPaginationCustom = new PaginationCustom<Category>(List.of(category),0,1,1L,1,true);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());


        when(categoryRepository.findAll(pageable)).thenReturn(categoryEntityPage);
        when(paginationMapper.toPaginationCustom(categoryEntityPage)).thenReturn(categoryPaginationCustom);

        Optional<PaginationCustom<Category>> result = categoryAdapter.getAllCategories(0,10, true);

        verify(categoryRepository, times(1)).findAll(pageable);
        verify(paginationMapper, times(1)).toPaginationCustom(categoryEntityPage);

        assertTrue(result.isPresent());

        assertEquals(category,result.get().getContent().get(0));
        assertEquals(0,result.get().getPageNumber());
        assertEquals(1,result.get().getPageSize());
        assertEquals(1L,result.get().getTotalElements());
        assertEquals(1,result.get().getTotalPages());
        assertTrue(result.get().isLast());


    }

}