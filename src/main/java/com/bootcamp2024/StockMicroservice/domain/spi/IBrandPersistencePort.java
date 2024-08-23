package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Brand getBrand(String brandName);

}
