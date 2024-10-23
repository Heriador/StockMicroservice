package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.Mapper;


import com.bootcamp2024.StockMicroservice.domain.model.Brand;
import com.bootcamp2024.StockMicroservice.domain.model.Category;
import com.bootcamp2024.StockMicroservice.domain.model.Item;
import com.bootcamp2024.StockMicroservice.domain.model.PaginationCustom;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.BrandEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = {CategoryEntityMapper.class, BrandEntityMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PaginationMapper {


    @Mapping(target = "pageNumber", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginationCustom<Category> toPaginationCustom(Page<CategoryEntity> pageable);

    @Mapping(target = "pageNumber", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginationCustom<Brand> toBrandPaginationCustom(Page<BrandEntity> pageable);

    @Mapping(target = "pageNumber", source = "number")
    @Mapping(target = "pageSize", source = "size")
    PaginationCustom<Item> toItemPaginationCustom(Page<ItemEntity> pageable);
}
