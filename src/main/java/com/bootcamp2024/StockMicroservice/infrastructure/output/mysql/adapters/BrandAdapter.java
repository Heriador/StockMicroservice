package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.BrandAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.BrandNotFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.BrandEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {

        if(brandRepository.findByName(brand.getName()).isPresent()){
            throw new BrandAlreadyExistsException("Brand Already exists");
        }

        brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));

    }

    @Override
    public Brand getBrand(String brandName) {

        return brandEntityMapper.brandEntityToBrand(brandRepository.findByName(brandName).orElseThrow(BrandNotFoundException::new));
    }
}
