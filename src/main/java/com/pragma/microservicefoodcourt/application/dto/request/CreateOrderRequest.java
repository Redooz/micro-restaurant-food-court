package com.pragma.microservicefoodcourt.application.dto.request;

import com.pragma.microservicefoodcourt.application.dto.OrderDishItemDto;
import com.pragma.microservicefoodcourt.application.dto.RestaurantItemDto;

import java.util.List;

public record CreateOrderRequest(
    List<OrderDishItemDto> orderDishes,
    RestaurantItemDto restaurant
) {
}
