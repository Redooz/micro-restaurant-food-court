package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateRestaurantRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetRestaurantResponse;
import com.pragma.microservicefoodcourt.application.handler.RestaurantHandler;
import com.pragma.microservicefoodcourt.infrastructure.driving.http.constant.RestaurantControllerConstant;
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
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "The Restaurant Endpoint")
public class RestaurantControllerAdapter {
    private final RestaurantHandler restaurantHandler;

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid CreateRestaurantRequest request) {
        restaurantHandler.createRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurants found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<List<GetRestaurantResponse>> findAllRestaurants(
            @RequestParam(defaultValue = RestaurantControllerConstant.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = RestaurantControllerConstant.DEFAULT_SIZE) int size) {
        return ResponseEntity.ok(restaurantHandler.findAllRestaurants(page, size));
    }
}
