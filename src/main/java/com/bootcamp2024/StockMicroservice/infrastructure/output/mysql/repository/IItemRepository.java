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

    @Query("SElECT i FROM ItemEntity i ORDER BY i.name ASC ")
    Page<ItemEntity> findAllOrderByNameAsc(Pageable pageable);

    @Query("SElECT i FROM ItemEntity i ORDER BY i.name DESC ")
    Page<ItemEntity> findAllOrderByNameDesc(Pageable pageable);

    @Query("SElECT i FROM ItemEntity i JOIN i.brand b ORDER BY b.name ASC ")
    Page<ItemEntity> findAllOrderByBrandCountAsc(Pageable pageable);

    @Query("SElECT i FROM ItemEntity i JOIN i.brand b ORDER BY b.name DESC ")
    Page<ItemEntity> findAllOrderByBrandCountDesc(Pageable pageable);

    @Query("SELECT i FROM ItemEntity i LEFT JOIN i.categories c GROUP BY i.id " +
            "ORDER BY COUNT(c.id) DESC, MIN(c.name) ASC")
    Page<ItemEntity> findAllOrderByCategoryCountDesc(Pageable pageable);

    @Query("SELECT i FROM ItemEntity i LEFT JOIN i.categories c GROUP BY i.id " +
            "ORDER BY COUNT(c.id) ASC, MIN(c.name) ASC")
    Page<ItemEntity> findAllOrderByCategoryCountAsc(Pageable pageable);

    @Query("SELECT i FROM ItemEntity i JOIN i.categories c WHERE i.id IN :itemIds " +
            "AND (:brandName IS NULL OR i.brand.name = :brandName) " +
            "AND (:categoryName IS NULL OR c.name = :categoryName)"
    )
    Page<ItemEntity> findAllByIdInAndFilters(@Param("itemIds") List<Long> itemIds,
                                             @Param("brandName") String brandName,
                                             @Param("categoryName") String categoryName,
                                             Pageable pageable);



}
