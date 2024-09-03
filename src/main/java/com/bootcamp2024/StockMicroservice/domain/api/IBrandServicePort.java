package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;

import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;


public interface IBrandServicePort {
    void saveBrand(Brand brand);


    Brand findByName(String brandName);

    Brand findById(Long brandId);

    BrandPaginationCustom getAllaBrands(int page, int size, boolean ord);


}
