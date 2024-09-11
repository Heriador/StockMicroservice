package com.bootcamp2024.StockMicroservice.domain.util;

public enum SortBy {

    NAME("name"),
    BRAND("brand.name"),
    CATEGORY("categories.name");

    private final String fieldName;

    SortBy(String fieldName){
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }

}
