package com.bootcamp2024.StockMicroservice.domain.util;

import com.bootcamp2024.StockMicroservice.domain.exception.ValidationException;
import com.bootcamp2024.StockMicroservice.domain.model.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ItemValidator {

    private ItemValidator() {
    }

    public static void validate(Item item){
        Map<String, String> errors = new HashMap<>();


        if(item.getPrice().compareTo(BigDecimal.ZERO) < 0){
            errors.put("price", DomainConstants.ITEM_FIELD_PRICE_NOT_POSITIVE_OR_ZERO_MESSAGE);
        }
        if(item.getStock() < 0L){
            errors.put("stock", DomainConstants.ITEM_FIELD_STOCK_NOT_POSITIVE_OR_ZERO_MESSAGE);
        }

        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }

    }

}
