package com.bootcamp2024.StockMicroservice.application.mapper;

import com.bootcamp2024.StockMicroservice.application.dto.response.ItemResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {CategoryResponseMapper.class, IBrandResponseMapper.class },
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IItemResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    ItemResponse toItemResponse(Item item);
}
