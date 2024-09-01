package com.bootcamp2024.StockMicroservice.application.mapper;

import com.bootcamp2024.StockMicroservice.application.dto.response.GetAllCategories;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", uses = {CategoryResponseMapper.class},
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GetAllCategoriesMapper {


    @Mapping(source = "content", target = "content")
    @Mapping(source = "pageNumber", target = "pageNumber")
    @Mapping(source = "pageSize", target = "pageSize")
    @Mapping(source = "totalElements", target = "totalElements")
    @Mapping(source = "totalPages", target = "totalPages")
    @Mapping(source = "last", target = "last")
    GetAllCategories paginationCustomToGetAllCategories(PaginationCustom paginationCustom);

}
