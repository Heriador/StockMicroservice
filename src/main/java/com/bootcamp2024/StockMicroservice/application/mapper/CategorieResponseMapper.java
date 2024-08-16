package com.bootcamp2024.StockMicroservice.application.mapper;

import com.bootcamp2024.StockMicroservice.application.dto.CategorieResponse;
import com.bootcamp2024.StockMicroservice.domain.model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategorieResponseMapper {


    CategorieResponse toResponse(Categorie categorie);


    default List<CategorieResponse> toResponseList(List<Categorie> categorieList){
        return categorieList.stream()
                .map(categorie -> {
                    CategorieResponse categorieResponse = new CategorieResponse();
                    categorieResponse.setName(categorie.getName());
                    categorieResponse.setDescription(categorie.getDescription());
                    return categorieResponse;
                }).toList();
    }

}
