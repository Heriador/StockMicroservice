package com.bootcamp2024.StockMicroservice.application.mapper;


import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    BrandResponse toResponse(Brand brand);

}
