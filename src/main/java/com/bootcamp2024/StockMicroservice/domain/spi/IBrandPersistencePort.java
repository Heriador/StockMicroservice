package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;

import java.util.Optional;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Optional<Brand> findByName(String brandName);

    Optional<Brand> findById(Long brandId);

}
