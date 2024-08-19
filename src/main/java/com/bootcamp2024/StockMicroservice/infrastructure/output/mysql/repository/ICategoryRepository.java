package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository;

import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String categoryName);

}
