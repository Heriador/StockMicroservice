package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.CategorieRequest;
import com.bootcamp2024.StockMicroservice.application.dto.CategorieResponse;

import java.util.List;

public interface ICategorieHandler {

    void createCategorie(CategorieRequest categorieRequest);

    List<CategorieResponse> getAllcategories();

    CategorieResponse getCategorie(String categorieName);

    void updateCategorie(CategorieRequest categorieRequest);

    void deleteCategorie(String categorieName);

}
