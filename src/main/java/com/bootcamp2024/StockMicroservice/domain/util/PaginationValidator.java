package com.bootcamp2024.StockMicroservice.domain.util;


import com.bootcamp2024.StockMicroservice.domain.exception.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class PaginationValidator {

    private PaginationValidator(){

    }

    public static void validate(int page, int size, String sortBy){
        Map<String, String> errors = new HashMap<>();
        if(page < PaginationConstants.MIN_PAGE_NUMBER){
            errors.put("page", PaginationConstants.PAGE_NUMBER_NOT_POSITIVE_MESSAGE);
        }
        if(size < PaginationConstants.MIN_PAGE_SIZE){
            errors.put("size", PaginationConstants.PAGE_SIZE_LOWER_THAN_MIN);
        }
        if(!SortByValidator.isValid(sortBy.toLowerCase())){
            errors.put("sortBy", PaginationConstants.INVALID_SORT_BY);
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }


}
