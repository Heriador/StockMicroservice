package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.BrandRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.BrandResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;



    @Override
    public void createBrand(AddBrand addBrand) {
        Brand brand = brandRequestMapper.addBrandToBrand(addBrand);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public BrandResponse getBrand(String brandName) {

        Brand brand = brandServicePort.getBrand(brandName);

        return brandResponseMapper.toResponse(brand);
    }
}