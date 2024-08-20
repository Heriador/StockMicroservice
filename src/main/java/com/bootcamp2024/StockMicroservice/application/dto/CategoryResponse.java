package com.bootcamp2024.StockMicroservice.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
}
