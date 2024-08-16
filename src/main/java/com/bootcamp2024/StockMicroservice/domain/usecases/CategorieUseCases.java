package com.bootcamp2024.StockMicroservice.domain.usecases;

import com.bootcamp2024.StockMicroservice.domain.ICategoriePersistencePort;
import com.bootcamp2024.StockMicroservice.domain.ICategorieServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Categorie;

import java.util.List;

public class CategorieUseCases implements ICategorieServicePort {

    private final ICategoriePersistencePort categoriePersistencePort;

    public CategorieUseCases(ICategoriePersistencePort categoriePersistencePort) {
        this.categoriePersistencePort = categoriePersistencePort;
    }

    @Override
    public void saveCategorie(Categorie categorie) {
        categoriePersistencePort.saveCategorie(categorie);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categoriePersistencePort.getAllCategories();
    }

    @Override
    public Categorie getCategorie(String categorieName) {
        return categoriePersistencePort.getCategorie(categorieName);
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        categoriePersistencePort.updateCategorie(categorie);
    }

    @Override
    public void deleteCategorie(String categorieName) {
        categoriePersistencePort.deleteCategorie(categorieName);
    }
}
