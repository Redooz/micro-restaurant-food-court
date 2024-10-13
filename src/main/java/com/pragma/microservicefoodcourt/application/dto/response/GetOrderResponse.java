package com.pragma.microservicefoodcourt.application.dto.response;

import com.pragma.microservicefoodcourt.application.dto.OrderDishItemDto;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record GetOrderResponse (
        List<OrderDishItemDto> orderDishes,
        LocalDate date,
        Long id,
        OrderStatus status,
        GetRestaurantResponse restaurant,
        String chefId,
        String clientId
){}
