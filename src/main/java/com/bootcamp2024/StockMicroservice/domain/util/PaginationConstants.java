package com.bootcamp2024.StockMicroservice.domain.util;

public class PaginationConstants {

    private PaginationConstants(){

    }

    public static final int MIN_PAGE_NUMBER = 0;
    public static final int MIN_PAGE_SIZE = 1;
    public static final String PAGE_NUMBER_NOT_POSITIVE_MESSAGE = "Page number must be positive";
    public static final String PAGE_SIZE_LOWER_THAN_MIN = "Page size must be greater than or equal to "+MIN_PAGE_SIZE;
    public static final String INVALID_SORT_BY = "Invalid sortBy field";
}
