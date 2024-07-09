package com.pragma.microservicefoodcourt.application.dto.response;

public record GetCategoryResponse(
        Long id,
        String name,
        String description
) {
}
