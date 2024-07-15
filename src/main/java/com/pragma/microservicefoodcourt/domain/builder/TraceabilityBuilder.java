package com.pragma.microservicefoodcourt.domain.builder;

import com.pragma.microservicefoodcourt.domain.model.Traceability;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;

import java.time.LocalDateTime;

public class TraceabilityBuilder {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String employeeId;
    private String id;
    private Long orderId;
    private OrderStatus lastStatus;
    private OrderStatus newStatus;
    private String clientEmail;
    private String clientId;
    private String employeeEmail;

    public TraceabilityBuilder setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public TraceabilityBuilder setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public TraceabilityBuilder setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public TraceabilityBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public TraceabilityBuilder setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public TraceabilityBuilder setLastStatus(OrderStatus lastStatus) {
        this.lastStatus = lastStatus;
        return this;
    }

    public TraceabilityBuilder setNewStatus(OrderStatus newStatus) {
        this.newStatus = newStatus;
        return this;
    }

    public TraceabilityBuilder setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
        return this;
    }

    public TraceabilityBuilder setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public TraceabilityBuilder setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
        return this;
    }

    public Traceability createTraceability() {
        return new Traceability(startTime, endTime, employeeId, id, orderId, lastStatus, newStatus, clientEmail, clientId, employeeEmail);
    }
}