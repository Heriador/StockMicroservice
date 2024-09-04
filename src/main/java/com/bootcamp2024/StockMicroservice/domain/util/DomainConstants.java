package com.bootcamp2024.StockMicroservice.domain.util;

public final class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
        PRICE,
        STOCK,


    }

    public static final int FIELD_NAME_MAX = 50;
    public static final int FIELD_DESCRIPTION_MAX = 90;
    public static final int BRAND_FIELD_DESCRIPTION_MAX = 120;
    public static final int ITEM_FIELD_CATEGORIES_MIN = 1;
    public static final int ITEM_FIELD_CATEGORIES_MAX = 3;


    public static final String FIELD_NAME_BLANK_MESSAGE = "Field 'name' cannot be blank";
    public static final String FIELD_DESCRIPTION_BLANK_MESSAGE = "Field 'description' cannot be blank";
    public static final String FIELD_NAME_MAX_EXCEEDED_MESSAGE =  "Field 'name' must be less than or equal to 50 Characters";
    public static final String FIELD_DESCRIPTION_MAX_EXCEEDED_MESSAGE = "Field 'description' must be less than or equal to 90 Characters";
    public static final String BRAND_FIELD_DESCRIPTION_MAX_EXCEEDED_MESSAGE = "Field 'description' must be less than or equal to 120 Characters";
    public static final String ITEM_FIELD_PRICE_NOT_POSITIVE_OR_ZERO_MESSAGE = "Field 'price' cannot be a negative value ";
    public static final String ITEM_FIELD_PRICE_NOT_NULL_MESSAGE = "Field 'price' cannot be empty";
    public static final String ITEM_FIELD_STOCK_NOT_POSITIVE_OR_ZERO_MESSAGE = "Field 'stock' cannot be a negative value ";
    public static final String ITEM_FIELD_STOCK_NOT_NULL_MESSAGE = "Field 'stock' cannot be empty";
    public static final String ITEM_FIELD_CATEGORIES_OUT_OF_RANK_MESSAGE = "Field 'categories' should have 1 to 3 elements, no more or less";
    public static final String ITEM_FIELD_CATEGORIES_NOT_UNIQUE_MESSAGE = "Field 'categories' should only contain unique elements";
    public static final String ITEM_FIELD_CATEGORIES_NOT_POSITIVE_MESSAGE = "Field 'categories' should only contain positive elements";
    public static final String ADDITEM_FIELD_BRANDID_NOT_POSITIVE_MESSAGE = "Field 'brandId' can only be a positive value";
    public static final String ADDITEM_FIELD_BRANDID_NOT_NULL_MESSAGE = "Field 'brandId' cannot be empty";
}
