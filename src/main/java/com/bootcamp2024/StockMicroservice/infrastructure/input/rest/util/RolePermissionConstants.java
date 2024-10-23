package com.bootcamp2024.StockMicroservice.infrastructure.input.rest.util;

public class RolePermissionConstants {

    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String HAS_ROLE_WAREHOUSE_ASSISTANT = "hasRole('WAREHOUSE_ASSISTANT')";
    public static final String PERMIT_ALL = "permitAll()";
    public static final String HAS_ROLE_CLIENT = "hasRole('CLIENT')";


    private RolePermissionConstants() {
    }
}
