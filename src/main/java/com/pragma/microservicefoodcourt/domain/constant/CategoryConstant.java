package com.pragma.microservicefoodcourt.domain.constant;

public class CategoryConstant {
    private CategoryConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE = "Category with the id %s was not found.";
}
