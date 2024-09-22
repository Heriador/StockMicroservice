package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository;

import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByName(String itemName);

    Boolean existsByIdAndStockGreaterThanEqual(Long itemId, Long quantity);
}
