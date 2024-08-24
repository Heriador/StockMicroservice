package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.BrandPaginationResponse;
import com.bootcamp2024.StockMicroservice.application.dto.BrandResponse;

public interface IBrandHandler {
    void createBrand(AddBrand addBrand);

    BrandResponse getBrand(String brandName);

    BrandPaginationResponse getAllBrands(int page, int size, boolean ord);
}
