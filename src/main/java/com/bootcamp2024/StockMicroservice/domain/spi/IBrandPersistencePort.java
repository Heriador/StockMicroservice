package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;


import java.util.Optional;


public interface IBrandPersistencePort {

    void saveBrand(Brand brand);


    Optional<Brand> findByName(String brandName);

    Optional<Brand> findById(Long brandId);

    Optional<PaginationCustom<Brand>> getAllBrands(int page, int size, boolean ord);


}
