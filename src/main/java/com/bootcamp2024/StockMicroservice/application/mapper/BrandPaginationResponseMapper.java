package com.bootcamp2024.StockMicroservice.application.mapper;

import com.bootcamp2024.StockMicroservice.application.dto.BrandPaginationResponse;
import com.bootcamp2024.StockMicroservice.domain.model.BrandPaginationCustom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {BrandResponseMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandPaginationResponseMapper {



    BrandPaginationResponse toPaginationResponse(BrandPaginationCustom brandPaginationCustom);
}
