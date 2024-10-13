package com.pragma.microservicefoodcourt.infrastructure.driven.client.dto.request;

import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;

import java.time.LocalDateTime;

public record UpdateTraceabilityRequest(
        String employeeId,

        String employeeEmail,

        OrderStatus lastStatus,

        OrderStatus newStatus,
        
        LocalDateTime endTime
) {
}
