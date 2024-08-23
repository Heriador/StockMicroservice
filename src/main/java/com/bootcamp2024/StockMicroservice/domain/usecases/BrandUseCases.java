package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.api.IBrandServicePort;
import com.bootcamp2024.StockMicroservice.domain.exception.EmptyFieldException;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
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

        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Brand getBrand(String brandName) {
        return brandPersistencePort.getBrand(brandName);
    }
}
