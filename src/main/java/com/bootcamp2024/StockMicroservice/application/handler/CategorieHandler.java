package com.bootcamp2024.StockMicroservice.application.handler;

import com.bootcamp2024.StockMicroservice.application.dto.CategorieRequest;
import com.bootcamp2024.StockMicroservice.application.dto.CategorieResponse;
import com.bootcamp2024.StockMicroservice.application.mapper.CategorieRequestMapper;
import com.bootcamp2024.StockMicroservice.application.mapper.CategorieResponseMapper;
import com.bootcamp2024.StockMicroservice.domain.ICategorieServicePort;
import com.bootcamp2024.StockMicroservice.domain.model.Categorie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategorieHandler implements ICategorieHandler{

    private final ICategorieServicePort categorieServicePort;
    private final CategorieRequestMapper categorieRequestMapper;
    private final CategorieResponseMapper categorieResponseMapper;

    @Override
    public void createCategorie(CategorieRequest categorieRequest) {
        Categorie categorie = categorieRequestMapper.toCategorie(categorieRequest);
        categorieServicePort.saveCategorie(categorie);

    }

    @Override
    public List<CategorieResponse> getAllcategories() {
        return categorieResponseMapper.toResponseList(categorieServicePort.getAllCategories());
    }

    @Override
    public CategorieResponse getCategorie(String categorieName) {
        Categorie categorie = categorieServicePort.getCategorie(categorieName);
        return categorieResponseMapper.toResponse(categorie);
    }

    @Override
    public void updateCategorie(CategorieRequest categorieRequest) {

        Categorie oldCategorie = categorieServicePort.getCategorie(categorieRequest.getName());

        Categorie newCategorie = categorieRequestMapper.toCategorie(categorieRequest);

        newCategorie.setId(oldCategorie.getId());

        categorieServicePort.updateCategorie(newCategorie);

    }

    @Override
    public void deleteCategorie(String categorieName) {

        categorieServicePort.deleteCategorie(categorieName);

    }
}
