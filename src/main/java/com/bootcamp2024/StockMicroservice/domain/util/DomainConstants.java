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

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_NAME_MAX_EXCEEDED_MESSAGE =  "Field 'name' cannot be longer than 50 Characters";
    public static final String FIELD_DESCRIPTION_MAX_EXCEEDED_MESSAGE = "Field 'description' cannot be longer than 50 Characters";
}
