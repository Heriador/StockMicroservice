package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;

import com.bootcamp2024.StockMicroservice.domain.exception.BrandAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.domain.exception.BrandNotFoundException;
import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;

import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;

import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;

public class BrandUseCases implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCases(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {

        if(brand.getName().trim().isEmpty()){
            throw  new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if(brand.getDescription().trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }

        if(brandPersistencePort.findByName(brand.getName()).isPresent()){
            throw new BrandAlreadyExistsException();
        }


        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Brand findByName(String brandName) {
        return brandPersistencePort.findByName(brandName).orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public Brand findById(Long brandId) {
        return brandPersistencePort.findById(brandId).orElseThrow(BrandNotFoundException::new);

 
    @Override
    public BrandPaginationCustom getAllaBrands(int page, int size, boolean ord) {
        return brandPersistencePort.getAllBrands(page, size, ord);
    }
}
