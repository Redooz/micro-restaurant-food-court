package com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.response;

import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;

import java.time.LocalDate;

public record GetTraceabilityResponse(
        String id,
        Long orderId,
        String clientId,
        String clientEmail,
        String employeeId,
        String employeeEmail,
        LocalDate date,
        OrderStatus lastStatus,
        OrderStatus newStatus
) {
}
