package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateOrderRequest;
import com.pragma.microservicefoodcourt.application.handler.OrderHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderControllerAdapterTest {

    @Mock
    private OrderHandler orderHandler;

    @InjectMocks
    private OrderControllerAdapter orderControllerAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Creating order successfully returns status CREATED")
    void creatingOrderSuccessfullyReturnsStatusCreated() {
        // Arrange
        doNothing().when(orderHandler).createOrder(any(CreateOrderRequest.class));

        // Act
        CreateOrderRequest request = CreateOrderRequest.builder().build();
        ResponseEntity<Void> response = orderControllerAdapter.createOrder(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}