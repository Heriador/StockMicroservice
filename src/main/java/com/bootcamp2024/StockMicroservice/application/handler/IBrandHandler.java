package com.bootcamp2024.StockMicroservice.application.handler;


import com.bootcamp2024.StockMicroservice.application.dto.request.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.dto.BrandPaginationResponse;


public interface IBrandHandler {
    void createBrand(AddBrand addBrand);


    BrandResponse findByName(String brandName);

    BrandResponse findById(Long brandId);


    BrandPaginationResponse getAllBrands(int page, int size, boolean ord);

}
