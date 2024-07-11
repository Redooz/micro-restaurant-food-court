package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateOrderRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetOrderResponse;
import com.pragma.microservicefoodcourt.application.handler.OrderHandler;
import com.pragma.microservicefoodcourt.domain.model.OrderStatus;
import com.pragma.microservicefoodcourt.infrastructure.driving.http.constant.GetAllConstant;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "The Order Endpoint")
public class OrderControllerAdapter {
    private final OrderHandler orderHandler;

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "409", description = "Dish is not from restaurant"),
            @ApiResponse(responseCode = "403", description = "Permission denied"),
            @ApiResponse(responseCode = "404", description = "Provided restaurant not found"),
            @ApiResponse(responseCode = "422", description = "User has a processing order")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        orderHandler.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders found"),
            @ApiResponse(responseCode = "403", description = "Permission denied"),
            @ApiResponse(responseCode = "404", description = "No data found for orders")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<GetOrderResponse>> findAllOrdersByStatusAndRestaurant(
            @RequestParam String nit,
            @RequestParam(defaultValue = "PENDING") String status,
            @RequestParam(defaultValue = GetAllConstant.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = GetAllConstant.DEFAULT_SIZE) int size
    )
    {
        return ResponseEntity.ok(orderHandler.findAllOrdersByStatusAndRestaurant(nit, OrderStatus.valueOf(status), page, size));
    }

    @PatchMapping("/{orderId}/assign")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order assigned"),
            @ApiResponse(responseCode = "403", description = "Permission denied"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> assignOrderToEmployee(@PathVariable Long orderId) {
        orderHandler.assignOrderToEmployee(orderId);
        return ResponseEntity.noContent().build();
    }

}
