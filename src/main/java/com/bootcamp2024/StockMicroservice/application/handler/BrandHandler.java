package com.bootcamp2024.StockMicroservice.application.handler;


import com.bootcamp2024.StockMicroservice.application.dto.request.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.IBrandRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.IBrandResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.application.dto.BrandPaginationResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.BrandPaginationResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;

    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;
    private final BrandPaginationResponseMapper brandPaginationResponseMapper;



    @Override
    public void createBrand(AddBrand addBrand) {
        Brand brand = brandRequestMapper.addBrandToBrand(addBrand);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public BrandResponse findByName(String brandName) {

        Brand brand = brandServicePort.findByName(brandName);

        return brandResponseMapper.toResponse(brand);
    }

    @Override
    public BrandResponse findById(Long brandId) {

        Brand brand = brandServicePort.findById(brandId);

        return brandResponseMapper.toResponse(brand);
    }

    public BrandPaginationResponse getAllBrands(int page, int size, boolean ord) {

        BrandPaginationCustom brandPaginationResponse = brandServicePort.getAllaBrands(page, size, ord);

        return brandPaginationResponseMapper.toPaginationResponse(brandPaginationResponse);
    }


}
