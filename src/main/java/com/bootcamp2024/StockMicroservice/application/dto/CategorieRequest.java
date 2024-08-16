package com.bootcamp2024.StockMicroservice.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategorieRequest {
    private String name;
    private String description;
}
