package com.bootcamp2024.StockMicroservice.application.dto;


import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class GetAllCategories {
    private List<CategoryResponse> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
