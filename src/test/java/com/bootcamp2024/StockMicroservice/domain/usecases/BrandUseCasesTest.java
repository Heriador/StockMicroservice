package com.bootcamp2024.StockMicroservice.domain.usecases;


import com.bootcamp2024.StockMicroservice.domain.exception.BrandAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.exception.BrandNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class BrandUseCasesTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCases brandUseCases;

    @Autowired
    private static Brand brand;

    @Autowired
    private static PaginationCustom<Brand> paginationCustom;

    @BeforeAll
    static void beforeAll() {
        brand = new Brand();
        brand.setName("LG");
        brand.setDescription("marca de televisores");

        paginationCustom = new PaginationCustom<>();
        paginationCustom.setContent(List.of(brand));
        paginationCustom.setPageNumber(0);
        paginationCustom.setPageSize(1);
        paginationCustom.setTotalElements(1L);
        paginationCustom.setTotalPages(1);
        paginationCustom.setLast(true);
    }

    @Test
    @DisplayName("Calling useCase saveBrand should Pass")
    void saveBrandShouldPass(){

        doNothing().when(brandPersistencePort).saveBrand(brand);

        brandUseCases.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);

    }

    @Test
    @DisplayName("Calling useCase saveBrand should throw EmptyFieldException for field name")
    void saveCategoryShouldThrowNameEmptyFieldException(){
        Brand brandEmptyName = new Brand();

        brandEmptyName.setId(null);
        brandEmptyName.setName("");
        brandEmptyName.setDescription("Para ver television");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ()->brandUseCases.saveBrand(brandEmptyName));
        assertEquals(DomainConstants.Field.NAME.toString(),exception.getMessage());
    }

    @Test
    @DisplayName("Calling useCase saveBrand should throw EmptyFieldException for field description")
    void saveCategoryShouldThrowDescriptionEmptyFieldException(){
        Brand brandEmptyDescription = new Brand();

        brandEmptyDescription.setId(null);
        brandEmptyDescription.setName("LG");
        brandEmptyDescription.setDescription("");

        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ()->brandUseCases.saveBrand(brandEmptyDescription));
        assertEquals(DomainConstants.Field.DESCRIPTION.toString(),exception.getMessage());
    }

    @Test
    @DisplayName("Calling useCase saveBrand should throw BrandAlreadyExistsException")
    void saveBrandShouldThrowBrandAlreadyExistException(){

        when(brandPersistencePort.findByName(brand.getName())).thenReturn(Optional.of(brand));

        assertThrows(BrandAlreadyExistsException.class, ()-> brandUseCases.saveBrand(brand));
    }

    @Test
    @DisplayName("Calling useCase findByName should pass and return the same object send in the mock")
    void findByNameShouldPass(){

        when(brandPersistencePort.findByName("LG")).thenReturn(Optional.of(brand));

        Brand brand1 = brandUseCases.findByName("LG");

        verify(brandPersistencePort, times(1)).findByName("LG");
        assertEquals(brand, brand1);
    }

    @Test()
    @DisplayName("calling useCase findByName Should Throw BrandNotFoundException")
    void findByNameShouldThrowNotFoundException(){
        when(brandPersistencePort.findByName("LG")).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, ()->brandUseCases.findByName("LG"));
    }

    @Test
    @DisplayName("Calling useCase findById should pass and return the same object send in the mock")
    void findByIdShouldPass(){

        when(brandPersistencePort.findById(1L)).thenReturn(Optional.of(brand));

        Brand brand1 = brandUseCases.findById(1L);

        verify(brandPersistencePort, times(1)).findById(1L);
        assertEquals(brand, brand1);

    }

    @Test()
    @DisplayName("calling useCase findById Should Throw BrandNotFoundException")
    void findByIdShouldThrowNotFoundException(){
        when(brandPersistencePort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, ()->brandUseCases.findById(1L));
    }


    @Test
    @DisplayName("Calling useCase getAllBrands should pass and return a pagination response object")
    void getAllBrandsShouldPass(){

        when(brandPersistencePort.getAllBrands(0,10,true)).thenReturn(Optional.of(paginationCustom));

        PaginationCustom<Brand> result = brandUseCases.getAllBrands(0,10,true);

        verify(brandPersistencePort, times(1)).getAllBrands(0,10,true);
        assertEquals(paginationCustom, result);

    }



    @Test
    @DisplayName("Calling useCase getAllBrands Should return EmptyList and Throw NoDataFoundException")
    void getAllCategoriesShouldReturnEmptyList() {

        when(brandPersistencePort.getAllBrands(0,10, true)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> brandUseCases.getAllBrands(0, 10,true));
    }


}