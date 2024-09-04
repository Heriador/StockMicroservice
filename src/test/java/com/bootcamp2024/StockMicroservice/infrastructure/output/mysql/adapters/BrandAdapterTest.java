package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IBrandEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.PaginationMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.BrandEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IBrandRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @Mock
    private PaginationMapper paginationMapper;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @Autowired
    private static Brand brand;

    private static BrandEntity brandEntity;

    @BeforeAll
    static void beforeAll() {
        brand = new Brand(1L, "LG", "marca de televisores");

        brandEntity = new BrandEntity();
        brandEntity.setId(brand.getId());
        brandEntity.setName(brand.getName());
        brandEntity.setDescription(brand.getDescription());
    }

    @Test
    @DisplayName("Calling method saveBrand should pass")
    void saveBrandShouldPass(){

        when(brandEntityMapper.brandToBrandEntity(brand)).thenReturn(brandEntity);
        when(brandRepository.save(brandEntity)).thenReturn(brandEntity);

        brandAdapter.saveBrand(brand);

        verify(brandEntityMapper, times(1)).brandToBrandEntity(brand);
        verify(brandRepository, times(1)).save(brandEntity);

    }

    @Test
    @DisplayName("Calling method findByName should pass and return the same object send in the mock")
    void findByNameShouldPass(){
        Optional<BrandEntity> brandEntityOptional = Optional.of(brandEntity);

        when(brandRepository.findByName(brand.getName())).thenReturn(brandEntityOptional);
        when(brandEntityMapper.brandEntityToBrand(brandEntity)).thenReturn(brand);

        Optional<Brand> response = brandAdapter.findByName(brand.getName());



        assertEquals(Optional.of(brand), response);
    }

    @Test
    @DisplayName("Calling method getBrand should pass and return the same object send in the mock")
    void findByIdShouldPass(){
        Optional<BrandEntity> brandEntityOptional = Optional.of(brandEntity);

        when(brandRepository.findById(brand.getId())).thenReturn(brandEntityOptional);
        when(brandEntityMapper.brandEntityToBrand(brandEntity)).thenReturn(brand);

        Optional<Brand> response = brandAdapter.findById(brand.getId());



        assertEquals(Optional.of(brand), response);

    }

    @Test
    @DisplayName("Calling method getAllBrands should return the same object send in the mock")
    void getAllBrandsShouldPass(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("name").ascending());
        Page<BrandEntity> brandEntityPage = Mockito.mock(Page.class);


        PaginationCustom<Brand> brandPaginationCustom = new PaginationCustom<>(List.of(brand),0,1,1L,1,true);



        when(brandRepository.findAll(pageable)).thenReturn(brandEntityPage);
        when(paginationMapper.toBrandPaginationCustom(brandEntityPage)).thenReturn(brandPaginationCustom);

        Optional<PaginationCustom<Brand>> result = brandAdapter.getAllBrands(0,10,true);

        verify(brandRepository, times(1)).findAll(pageable);
        verify(paginationMapper, times(1)).toBrandPaginationCustom(brandEntityPage);

        assertTrue(result.isPresent());

        assertAll(
                () -> assertEquals(brand, result.get().getContent().get(0)),
                () -> assertEquals(0,result.get().getPageNumber()),
                () -> assertEquals(1,result.get().getPageSize()),
                () -> assertEquals(1L,result.get().getTotalElements()),
                () -> assertEquals(1,result.get().getTotalPages()),
                () -> assertTrue(result.get().isLast())


        );
    }

}