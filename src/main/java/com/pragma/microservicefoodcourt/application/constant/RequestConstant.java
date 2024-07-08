package com.pragma.microservicefoodcourt.application.constant;

public class RequestConstant {
    private RequestConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String REGEX_ONLY_NUMBERS = "^\\d+$";
    public static final String REGEX_PHONE = "^\\+\\d{11,12}$";
    public static final String REGEX_URL = "^(https?:\\/\\/)?((localhost|[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}))(\\:[0-9]{1,5})?(\\/.*|)$";
}
