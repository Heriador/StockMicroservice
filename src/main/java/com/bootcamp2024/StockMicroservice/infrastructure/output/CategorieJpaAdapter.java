package com.bootcamp2024.StockMicroservice.infrastructure.output;

import com.bootcamp2024.StockMicroservice.domain.ICategoriePersistencePort;
import com.bootcamp2024.StockMicroservice.domain.model.Categorie;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategorieAlreadyExistsException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.CategorieNotFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.exception.NoDataFoundException;
import com.bootcamp2024.StockMicroservice.infrastructure.output.Mapper.CategorieEntityMapper;
import com.bootcamp2024.StockMicroservice.infrastructure.output.entity.CategorieEntity;
import com.bootcamp2024.StockMicroservice.infrastructure.output.repository.ICategorieRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CategorieJpaAdapter implements ICategoriePersistencePort {

    private final ICategorieRepository categorieRepository;
    private final CategorieEntityMapper categorieEntityMapper;

    @Override
    public void saveCategorie(Categorie categorie) {
        if(categorieRepository.findByName(categorie.getName()).isPresent()){
            throw new CategorieAlreadyExistsException();
        }
        categorieRepository.save(categorieEntityMapper.categorietoEntity(categorie));
    }

    @Override
    public List<Categorie> getAllCategories() {
        List<CategorieEntity> categorieEntityList = categorieRepository.findAll();

        if(categorieEntityList.isEmpty()){
            throw new NoDataFoundException();
        }

        return categorieEntityMapper.toCategorieList(categorieEntityList);
    }

    @Override
    public Categorie getCategorie(String categorieName) {

        return categorieEntityMapper.entitytoCategorie(categorieRepository.findByName(categorieName)
                .orElseThrow(CategorieNotFoundException::new));
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        categorieRepository.save(categorieEntityMapper.categorietoEntity(categorie));

    }

    @Override
    public void deleteCategorie(String categorieName) {
        categorieRepository.deleteByName(categorieName);

    }
}
