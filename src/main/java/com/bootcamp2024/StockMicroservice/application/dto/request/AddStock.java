package com.bootcamp2024.StockMicroservice.application.dto.request;


import com.bootcamp2024.StockMicroservice.domain.util.DomainConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddStock {

    @NotNull(message = DomainConstants.QUANTITY_NULL_MESSAGE)
    private Integer quantity;
}
