package com.bootcamp2024.StockMicroservice.domain.spi;

import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Brand getBrand(String brandName);

    BrandPaginationCustom getAllBrands(int page, int size, boolean ord);

}
