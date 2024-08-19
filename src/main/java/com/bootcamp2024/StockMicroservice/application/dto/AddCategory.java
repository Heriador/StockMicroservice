package com.bootcamp2024.StockMicroservice.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddCategory {
    private String name;
    private String description;
}
