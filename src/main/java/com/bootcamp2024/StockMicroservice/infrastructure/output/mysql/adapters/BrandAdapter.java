package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.adapters;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;
import com.bootcamp2024.StockMicroservice.domain.spi.IBrandPersistencePort;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.BrandAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.BrandNotFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper.BrandEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.BrandEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public BrandPaginationCustom getAllBrands(int page, int size, boolean ord) {
        Sort sort = ord ? Sort.by("name").ascending() : Sort.by("name").descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageable);

        if(brandEntityPage.isEmpty()){
            throw new NoDataFoundException("No Brands found");
        }

        BrandPaginationCustom pagination = new BrandPaginationCustom();
        pagination.setContent(brandEntityMapper.toBrandList(brandEntityPage.getContent()));
        pagination.setPageNumber(brandEntityPage.getNumber());
        pagination.setPageSize(brandEntityPage.getSize());
        pagination.setTotalElements(brandEntityPage.getTotalElements());
        pagination.setTotalPages(brandEntityPage.getTotalPages());
        pagination.setLast(brandEntityPage.isLast());

        return pagination;

    }
}
