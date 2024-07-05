package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateRestaurantRequest;
import com.pragma.microservicefoodcourt.application.handler.RestaurantHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "The Restaurant Endpoint")
public class RestaurantControllerAdapter {
    private final RestaurantHandler restaurantHandler;

    @PostMapping("/")
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid CreateRestaurantRequest request) {
        restaurantHandler.createRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
