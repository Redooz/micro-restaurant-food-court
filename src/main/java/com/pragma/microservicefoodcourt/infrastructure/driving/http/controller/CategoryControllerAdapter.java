package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateCategoryRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetCategoryResponse;
import com.pragma.microservicefoodcourt.application.handler.CategoryHandler;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category", description = "The Category Endpoint")
public class CategoryControllerAdapter {
    private final CategoryHandler categoryHandler;

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        categoryHandler.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<List<GetCategoryResponse>> findAllCategories(
            @RequestParam(defaultValue = GetAllConstant.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = GetAllConstant.DEFAULT_SIZE) int size)
    {
        return ResponseEntity.ok(categoryHandler.findAllCategories(page, size));
    }
}
