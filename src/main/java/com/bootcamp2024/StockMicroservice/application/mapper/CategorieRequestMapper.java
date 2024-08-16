package com.bootcamp2024.StockMicroservice.application.mapper;


import com.bootcamp2024.StockMicroservice.application.dto.CategorieRequest;
import com.bootcamp2024.StockMicroservice.domain.model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategorieRequestMapper {

    @Mapping(source = "categorieRequest.name",target = "name")
    @Mapping(source = "categorieRequest.description",target = "description")
    Categorie toCategorie(CategorieRequest categorieRequest);

}
