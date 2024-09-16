package com.bootcamp2024.StockMicroservice.Factory;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddBrand;
import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.BrandEntity;

import java.util.List;

public class BrandFactory {

    private static final Brand brand;
    private static final BrandEntity brandEntity;
    private static final BrandResponse brandResponse;
    private static final AddBrand addBrand;

    private static final PaginationCustom<Brand> paginationCustom;
    private static final PaginationResponse<BrandResponse> paginationResponse;

    static {
        brand = new Brand();
        brand.setId(1L);
        brand.setName("brand");
        brand.setDescription("description");

        brandEntity = new BrandEntity();
        brandEntity.setId(brand.getId());
        brandEntity.setName(brand.getName());
        brandEntity.setDescription(brand.getDescription());

        brandResponse = new BrandResponse();
        brandResponse.setId(brand.getId());
        brandResponse.setName(brand.getName());
        brandResponse.setDescription(brand.getDescription());

        addBrand = new AddBrand();
        addBrand.setName(brand.getName());
        addBrand.setDescription(brand.getDescription());

        paginationCustom = new PaginationCustom<>(List.of(brand), 0, 1, 1L, 1, true);

        paginationResponse = new PaginationResponse<>();
        paginationResponse.setTotalPages(paginationCustom.getTotalPages());
        paginationResponse.setTotalElements(paginationCustom.getTotalElements());
        paginationResponse.setLast(paginationCustom.isLast());
        paginationResponse.setContent(List.of(brandResponse));
        paginationCustom.setPageNumber(paginationCustom.getPageNumber());
        paginationCustom.setPageSize(paginationCustom.getPageSize());
    }

    public static Brand getBrand(){
        return brand;
    }

    public static BrandEntity getBrandEntity(){
        return brandEntity;
    }

    public static BrandResponse getBrandResponse(){
        return brandResponse;
    }

    public static AddBrand getAddBrand(){
        return addBrand;
    }

}
