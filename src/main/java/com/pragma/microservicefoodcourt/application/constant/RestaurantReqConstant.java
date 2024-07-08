package com.pragma.microservicefoodcourt.application.constant;

public class RestaurantReqConstant {
    private RestaurantReqConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MSG_NIT_REQUIRED = "NIT is required";
    public static final String MSG_NIT_NUMBER_FORMAT = "NIT must be a number";
    public static final String MSG_ADDRESS_REQUIRED = "Address is required";
    public static final String MSG_NAME_REQUIRED = "Name is required";
    public static final String MSG_OWNER_ID_REQUIRED = "Owner ID is required";
    public static final String MSG_OWNER_ID_NUMBER_FORMAT = "Owner ID must be a number";
    public static final String MSG_PHONE_REQUIRED = "Phone is required";
    public static final String MSG_PHONE_FORMAT = "Phone must be in the format +11234567890";
    public static final String MSG_URL_LOGO_REQUIRED = "URL logo is required";
    public static final String MSG_URL_LOGO_FORMAT = "URL logo must be a valid URL";

    public static final String REGEX_NAME = "^(?!^\\d+$)[\\w\\s]+$";
}
