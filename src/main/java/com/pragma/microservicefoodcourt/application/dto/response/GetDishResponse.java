package com.pragma.microservicefoodcourt.application.dto.response;

import com.pragma.microservicefoodcourt.application.dto.CategoryItemDto;
import com.pragma.microservicefoodcourt.application.dto.RestaurantItemDto;

public record GetDishResponse(
        Long id,
        String name,
        Double price,
        String description,
        String urlImage,
        CategoryItemDto category,
        RestaurantItemDto restaurant,
        Boolean isActive
) {
}
