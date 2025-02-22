package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateOrderRequest;
import com.pragma.microservicefoodcourt.application.dto.request.DeliverOrderRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetOrderResponse;
import com.pragma.microservicefoodcourt.application.handler.OrderHandler;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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

    @Test
    @DisplayName("Finding all orders by status and restaurant with valid parameters returns status OK")
    void findAllOrdersByStatusAndRestaurantWithValidParametersReturnsStatusOk() {
        GetOrderResponse getOrderResponse = GetOrderResponse.builder().build();
        // Arrange
        when(orderHandler.findAllOrdersByStatusAndRestaurant(anyString(), any(OrderStatus.class), anyInt(), anyInt()))
                .thenReturn(List.of(getOrderResponse));

        // Act
        ResponseEntity<List<GetOrderResponse>> response = orderControllerAdapter.findAllOrdersByStatusAndRestaurant("123456789", "PENDING", 0, 5);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    @DisplayName("Assigning order to employee successfully returns status NO_CONTENT")
    void assigningOrderToEmployeeSuccessfullyReturnsStatusNoContent() {
        // Arrange
        doNothing().when(orderHandler).assignOrderToEmployee(anyLong());

        // Act
        ResponseEntity<Void> response = orderControllerAdapter.assignOrderToEmployee(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Finishing order successfully returns status NO_CONTENT")
    void finishingOrderSuccessfullyReturnsStatusNoContent() {
        // Arrange
        doNothing().when(orderHandler).finishOrder(anyLong());

        // Act
        ResponseEntity<Void> response = orderControllerAdapter.finishOrder(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Delivering order successfully returns status NO_CONTENT")
    void deliveringOrderSuccessfullyReturnsStatusNoContent() {
        Long orderId = 1L;
        DeliverOrderRequest request = DeliverOrderRequest.builder().code("123456").build();
        // Arrange
        doNothing().when(orderHandler).deliverOrder(anyLong(), any(DeliverOrderRequest.class));

        // Act
        ResponseEntity<Void> response = orderControllerAdapter.deliverOrder(1L, request);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Canceling order successfully returns status NO_CONTENT")
    void cancelingOrderSuccessfullyReturnsStatusNoContent() {
        // Arrange
        doNothing().when(orderHandler).cancelOrder(anyLong());

        // Act
        ResponseEntity<Void> response = orderControllerAdapter.cancelOrder(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}