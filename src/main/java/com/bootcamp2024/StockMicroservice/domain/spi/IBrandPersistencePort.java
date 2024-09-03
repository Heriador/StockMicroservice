package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;


import java.util.Optional;

import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;


public interface IBrandPersistencePort {

    void saveBrand(Brand brand);


    Optional<Brand> findByName(String brandName);

    Optional<Brand> findById(Long brandId);

    BrandPaginationCustom getAllBrands(int page, int size, boolean ord);


}
