package com.bootcamp2024.StockMicroservice.application.mapper;

import com.bootcamp2024.StockMicroservice.application.dto.response.BrandResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.CategoryResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.application.dto.response.PaginationResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", uses = {CategoryResponseMapper.class, IBrandResponseMapper.class, IItemResponseMapper.class},
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationResponseMapper {


    @Mapping(source = "content", target = "content")
    @Mapping(source = "pageNumber", target = "pageNumber")
    @Mapping(source = "pageSize", target = "pageSize")
    @Mapping(source = "totalElements", target = "totalElements")
    @Mapping(source = "totalPages", target = "totalPages")
    @Mapping(source = "last", target = "last")
    PaginationResponse<CategoryResponse> paginationCustomToGetAllCategories(PaginationCustom<Category> paginationCustom);

    PaginationResponse<BrandResponse> toBrandPaginationResponse(PaginationCustom<Brand> paginationCustom);

    PaginationResponse<ItemResponse> toItemPaginationResponse(PaginationCustom<Item> paginationCustom);
}
