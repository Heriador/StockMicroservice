package com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long stock;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(
            name = "item_category",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;

}
