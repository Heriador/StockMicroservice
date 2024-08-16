package com.bootcamp2024.StockMicroservice.infrastructure.output.repository;

import com.bootcamp2024.StockMicroservice.infrastructure.output.entity.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategorieRepository extends JpaRepository<CategorieEntity, Long> {

    Optional<CategorieEntity> findByName(String categorieName);

    void deleteByName(String categorieName);
}
