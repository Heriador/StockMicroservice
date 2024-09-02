package com.bootcamp2024.StockMicroservice.domain.util;

public final class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION
    }

    public static final int FIELD_NAME_MAX = 50;
    public static final int FIELD_DESCRIPTION_MAX = 90;

    public static final String FIELD_NAME_BLANK_MESSAGE = "Field 'name' cannot be blank";
    public static final String FIELD_DESCRIPTION_BLANK_MESSAGE = "Field 'description' cannot be blank";
    public static final String FIELD_NAME_MAX_EXCEEDED_MESSAGE =  "Field 'name' must be less than or equal to 50 Characters";
    public static final String FIELD_DESCRIPTION_MAX_EXCEEDED_MESSAGE = "Field 'description' must be less than or equal to 90 Characters";
}
