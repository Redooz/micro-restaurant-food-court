package com.pragma.microservicefoodcourt.domain.model;

import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;

import java.time.LocalDateTime;

public class Traceability {
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

    public Traceability(LocalDateTime startTime, LocalDateTime endTime, String employeeId, String id, Long orderId, OrderStatus lastStatus, OrderStatus newStatus, String clientEmail, String clientId, String employeeEmail) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.employeeId = employeeId;
        this.id = id;
        this.orderId = orderId;
        this.lastStatus = lastStatus;
        this.newStatus = newStatus;
        this.clientEmail = clientEmail;
        this.clientId = clientId;
        this.employeeEmail = employeeEmail;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(OrderStatus lastStatus) {
        this.lastStatus = lastStatus;
    }

    public OrderStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(OrderStatus newStatus) {
        this.newStatus = newStatus;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}
