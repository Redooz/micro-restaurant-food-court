package com.pragma.microservicefoodcourt.application.dto;

public record OrderDishItemDto(
    DishItemDto dish,
    int quantity
) {
}