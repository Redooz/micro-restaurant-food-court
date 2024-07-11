package com.pragma.microservicefoodcourt.domain.constant;

public class OrderConstant {
    private OrderConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMPLOYEE_DOES_NOT_BELONG_TO_RESTAURANT = "Employee with documentId %s does not belong to restaurant";
    public static final String DISH_IS_NOT_FROM_RESTAURANT = "Some dishes are not from restaurant with nit %s";
    public static final String USER_HAS_PROCESSING_ORDER = "User with documentId %s has a processing order";
}
