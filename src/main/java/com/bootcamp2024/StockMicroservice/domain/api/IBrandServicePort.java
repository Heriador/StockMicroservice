package com.bootcamp2024.StockMicroservice.domain.api;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;

public interface IBrandServicePort {
    void saveBrand(Brand brand);

    Brand getBrand(String brandName);

    BrandPaginationCustom getAllaBrands(int page, int size, boolean ord);

}
