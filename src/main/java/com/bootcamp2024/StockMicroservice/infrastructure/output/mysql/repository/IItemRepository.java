package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.repository;

import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByName(String itemName);

    Boolean existsByIdAndStockGreaterThanEqual(Long itemId, Long quantity);

    @Query("SELECT i FROM ItemEntity i JOIN i.categories c WHERE i.id IN :itemIds " +
            "AND (:brandName IS NULL OR i.brand.name = :brandName) " +
            "AND (:categoryName IS NULL OR c.name = :categoryName)"
    )
    Page<ItemEntity> findAllByIdInAndFilters(@Param("itemIds") List<Long> itemIds,
                                             @Param("brandName") String brandName,
                                             @Param("categoryName") String categoryName,
                                             Pageable pageable);
}
