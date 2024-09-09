package com.bootcamp2024.StockMicroservice.domain.util;

import java.util.Set;

public class SortByValidator {
    private static final Set<String> VALID_SORT_BY = Set.of(
            SortBy.NAME.toString(),
            SortBy.BRAND.toString(),
            SortBy.CATEGORY.toString()
    );
    private SortByValidator() {
    }
    public static boolean isValid(String sortBy) {
        return VALID_SORT_BY.contains(sortBy);
    }

}
