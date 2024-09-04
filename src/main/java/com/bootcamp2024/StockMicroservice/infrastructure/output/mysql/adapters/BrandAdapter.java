package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;

import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.IBrandEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.PaginationMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.BrandEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private final PaginationMapper paginationMapper;


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

    @Override
    public Optional<PaginationCustom<Brand>> getAllBrands(int page, int size, boolean ord) {
        Sort sort = ord ? Sort.by("name").ascending() : Sort.by("name").descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageable);

        PaginationCustom<Brand> brandPaginationCustom = paginationMapper.toBrandPaginationCustom(brandEntityPage);

        if(brandPaginationCustom.getContent() == null || brandPaginationCustom.getContent().isEmpty()){
            return Optional.empty();
        }



        return Optional.of(brandPaginationCustom);

    }
}
