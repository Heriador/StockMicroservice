package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;

public interface IBrandServicePort {
    void saveBrand(Brand brand);

    Brand getBrand(String brandName);

}
