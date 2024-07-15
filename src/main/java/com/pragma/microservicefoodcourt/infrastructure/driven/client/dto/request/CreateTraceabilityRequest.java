package com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request;

import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;

import java.time.LocalDateTime;

public record CreateTraceabilityRequest(
        Long orderId,

        String clientId,

        String clientEmail,

        String restaurantNit,

        LocalDateTime startTime,

        OrderStatus newStatus
) {
}
