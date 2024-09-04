package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;


public interface IBrandServicePort {
    void saveBrand(Brand brand);


    Brand findByName(String brandName);

    Brand findById(Long brandId);

    PaginationCustom<Brand> getAllBrands(int page, int size, boolean ord);


}
