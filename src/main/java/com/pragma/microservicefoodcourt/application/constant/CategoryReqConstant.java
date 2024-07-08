package com.pragma.microservicefoodcourt.application.constant;

public class CategoryReqConstant {
    private CategoryReqConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MSG_ID_IS_REQUIRED = "Id is required";
    public static final String MSG_NAME_IS_REQUIRED = "Name is required";
    public static final String MSG_DESCRIPTION_IS_REQUIRED = "Description is required";
}
