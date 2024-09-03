package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IBrandEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.BrandEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {


        brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));

    }

    @Override
    public Optional<Brand> findByName(String brandName) {

        Optional<BrandEntity> brandEntity = brandRepository.findByName(brandName);

        return brandEntity.map(brandEntityMapper::brandEntityToBrand);
    }

    @Override
    public Optional<Brand> findById(Long brandId) {

        Optional<BrandEntity> brandEntity = brandRepository.findById(brandId);

        return brandEntity.map(brandEntityMapper::brandEntityToBrand);
    }
}
