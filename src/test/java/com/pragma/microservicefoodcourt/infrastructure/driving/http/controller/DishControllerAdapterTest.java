package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishRequest;
import com.pragma.microservicefoodcourt.application.handler.DishHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DishControllerAdapterTest {

    @Mock
    private DishHandler dishHandler;

    @InjectMocks
    private DishControllerAdapter dishControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create dish and return status 201")
    void shouldCreateDishAndReturnStatus201() {
        CreateDishRequest request = CreateDishRequest.builder().name("Test Dish").build();

        ResponseEntity<Void> response = dishControllerAdapter.createDish(request);

        verify(dishHandler, times(1)).createDish(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Should update dish and return status 204")
    void shouldUpdateDishAndReturnStatus204() {
        Long id = 1L;
        UpdateDishRequest request = UpdateDishRequest.builder()
                .price(10.0)
                .description("Test Dish")
                .build();

        ResponseEntity<Void> response = dishControllerAdapter.updateDish(id, request);

        verify(dishHandler, times(1)).updateDish(id, request);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}