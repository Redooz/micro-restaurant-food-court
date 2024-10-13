package com.pragma.microservicefoodcourt.application.constant;

public class OrderReqConstant {
    private OrderReqConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MSG_ORDER_DISHES_NOT_EMPTY = "orderDishes must not be empty";
    public static final String MSG_QUANTITY_MUST_BE_POSITIVE = "quantity must be positive";
    public static final String MSG_NIT_NOT_BLANK = "nit must not be blank";

    public static final int CODE_LENGTH = 6;
    public static final String MSG_CODE_LENGTH = "Code must be " + CODE_LENGTH + " characters";
    public static final String FORMAT_CODE_ERROR = "Code must be a number";

}
