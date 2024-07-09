package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishStatusRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetDishResponse;
import com.pragma.microservicefoodcourt.application.handler.DishHandler;
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
@RequestMapping("/dishes")
@RequiredArgsConstructor
@Tag(name = "Dish", description = "The Dish Endpoint")
public class DishControllerAdapter {
    private final DishHandler dishHandler;

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Permission denied"),
            @ApiResponse(responseCode = "404", description = "Provided category or restaurant not found")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> createDish(@RequestBody @Valid CreateDishRequest request) {
        dishHandler.createDish(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dish updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Permission denied"),
            @ApiResponse(responseCode = "404", description = "Dish not found")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> updateDish(@PathVariable Long id, @RequestBody @Valid UpdateDishRequest request) {
        dishHandler.updateDish(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dish status updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Permission denied"),
            @ApiResponse(responseCode = "404", description = "Dish not found")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> updateDishStatus(@PathVariable Long id, @RequestBody @Valid UpdateDishStatusRequest request) {
        dishHandler.updateDishStatus(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dishes retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Dishes not found")
    })
    public ResponseEntity<List<GetDishResponse>> getAllDishes(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = GetAllConstant.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = GetAllConstant.DEFAULT_SIZE) int size)
    {
        return ResponseEntity.ok(dishHandler.getAllDishes(categoryId, page, size));
    }
}
