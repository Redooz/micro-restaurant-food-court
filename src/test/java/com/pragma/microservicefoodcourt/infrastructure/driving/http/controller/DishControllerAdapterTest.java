package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishStatusRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetDishResponse;
import com.pragma.microservicefoodcourt.application.handler.DishHandler;
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

    @Test
    @DisplayName("Should update dish status and return status 204")
    void shouldUpdateDishStatusAndReturnStatus204() {
        Long id = 1L;
        UpdateDishStatusRequest request = new UpdateDishStatusRequest(true);

        ResponseEntity<Void> response = dishControllerAdapter.updateDishStatus(id, request);

        verify(dishHandler, times(1)).updateDishStatus(id, request);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Should get all dishes and return status 200")
    void shouldGetAllDishesAndReturnStatus200() {
        Long categoryId = 1L;
        int page = 0;
        int size = 10;
        ResponseEntity<List<GetDishResponse>> response = dishControllerAdapter.getAllDishes(categoryId, page, size);

        verify(dishHandler, times(1)).getAllDishes(categoryId, page, size);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}