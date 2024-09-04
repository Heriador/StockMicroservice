package com.bootcamp2024.StockMicroservice.application.handler;


import com.bootcamp2024.StockMicroservice.application.dto.request.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;


public interface IBrandHandler {
    void createBrand(AddBrand addBrand);


    BrandResponse findByName(String brandName);

    BrandResponse findById(Long brandId);


    PaginationResponse<BrandResponse> getAllBrands(int page, int size, boolean ord);

}
