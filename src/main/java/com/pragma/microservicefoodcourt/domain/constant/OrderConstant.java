package com.pragma.microservicefoodcourt.domain.constant;

public class OrderConstant {
    private OrderConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMPLOYEE_DOES_NOT_BELONG_TO_RESTAURANT = "Employee with documentId %s does not belong to restaurant";
    public static final String DISH_IS_NOT_FROM_RESTAURANT = "Some dishes are not from restaurant with nit %s";
    public static final String USER_HAS_PROCESSING_ORDER = "User with documentId %s has a processing order";
    public static final String ORDER_EMPTY_LIST = "No data found for orders";
    public static final String ORDER_NOT_FOUND = "Order with id %s not found";
    public static final String SENT_VERIFICATION_STATUS_ERROR = "Failed to send verification status: %s";
}
