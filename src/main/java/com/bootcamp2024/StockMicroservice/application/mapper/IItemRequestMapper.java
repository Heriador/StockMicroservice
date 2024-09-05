package com.bootcamp2024.StockMicroservice.application.mapper;

import com.bootcamp2024.StockMicroservice.application.dto.request.AddItem;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.usecases.BrandUseCases;
import com.bootcamp2024.StockMicroservice.domain.usecases.CategoryUseCases;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { CategoryUseCases.class, BrandUseCases.class },
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IItemRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "categories", target = "categories")
    Item toItem(AddItem addItem);
}
