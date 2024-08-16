package com.bootcamp2024.StockMicroservice.infrastructure.output.Mapper;

import com.bootcamp2024.StockMicroservice.domain.model.Categorie;
import com.bootcamp2024.StockMicroservice.infrastructure.output.entity.CategorieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategorieEntityMapper {


    CategorieEntity categorietoEntity(Categorie categorie);


    @Mapping(source = "categorieEntity.id", target = "id")
    @Mapping(source = "categorieEntity.name", target = "name")
    @Mapping(source = "categorieEntity.description", target = "description")
    Categorie entitytoCategorie(CategorieEntity categorieEntity);

    List<Categorie> toCategorieList(List<CategorieEntity> categorieEntityList);

}
