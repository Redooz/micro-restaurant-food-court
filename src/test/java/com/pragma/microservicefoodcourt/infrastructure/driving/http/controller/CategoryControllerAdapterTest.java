package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateCategoryRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetCategoryResponse;
import com.pragma.microservicefoodcourt.application.handler.CategoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerAdapterTest {

    @Mock
    private CategoryHandler categoryHandler;

    @InjectMocks
    private CategoryControllerAdapter categoryControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create category and return status 201")
    void shouldCreateCategoryAndReturnStatus201() {
        CreateCategoryRequest request = CreateCategoryRequest.builder().name("Test Category").build();

        ResponseEntity<Void> response = categoryControllerAdapter.createCategory(request);

        verify(categoryHandler, times(1)).createCategory(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Should find all categories and return status 200")
    void shouldFindAllCategories() {
        ResponseEntity<List<GetCategoryResponse>> response = categoryControllerAdapter.findAllCategories(1, 10);

        verify(categoryHandler, times(1)).findAllCategories(1, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
