package com.bootcamp2024.StockMicroservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
    private BrandResponse brand;
    private List<CategoryResponse> categories;
}
