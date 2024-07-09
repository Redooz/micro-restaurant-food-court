package com.pragma.microservicefoodcourt.application.dto.response;

public record GetRestaurantResponse(
        String nit,
        String name,
        String urlLogo
) {
}
