package com.bootcamp2024.StockMicroservice.application.mapper;


import com.bootcamp2024.StockMicroservice.application.dto.AddBrand;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Brand addBrandToBrand(AddBrand addBrand);

}
