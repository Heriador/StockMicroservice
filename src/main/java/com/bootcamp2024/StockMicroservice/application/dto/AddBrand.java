package com.bootcamp2024.StockMicroservice.application.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddBrand {
    private String name;
    private String description;
}
