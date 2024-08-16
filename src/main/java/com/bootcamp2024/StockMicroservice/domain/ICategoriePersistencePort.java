package com.bootcamp2024.StockMicroservice.domain;

import com.bootcamp2024.StockMicroservice.domain.model.Categorie;

import java.util.List;

public interface ICategoriePersistencePort {

    void saveCategorie(Categorie categorie);

    List<Categorie> getAllCategories();

    Categorie getCategorie(String categorieName);

    void updateCategorie(Categorie categorie);

    void deleteCategorie(String categorieName);

}
