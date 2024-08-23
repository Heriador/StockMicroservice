package com.bootcamp2024.StockMicroservice.infrastructure.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("utility class");
    }

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE = "The category indicated does not exist";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The category you want to create already exists";
    public static final String BRAND_NOT_FOUND_EXCEPTION_MESSAGE = "The brand indicated does not exist";
    public static final String BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The brand you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s can not be empty";

}
