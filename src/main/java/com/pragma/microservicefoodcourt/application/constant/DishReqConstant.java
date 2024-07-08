package com.pragma.microservicefoodcourt.application.constant;

public class DishReqConstant {
    private DishReqConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MSG_NAME_IS_REQUIRED = "Name is required";
    public static final String MSG_PRICE_IS_REQUIRED = "Price is required";
    public static final String MSG_DESCRIPTION_IS_REQUIRED = "Description is required";
    public static final String MSG_URL_IMAGE_IS_REQUIRED = "Url image is required";
    public static final String MSG_RESTAURANT_NIT_IS_REQUIRED = "Restaurant NIT is required";

    public static final String MSG_URL_IMAGE_FORMAT = "Url image format is invalid";
    public static final String MSG_PRICE_FORMAT = "Price format is invalid";
}
