package com.pragma.microservicefoodcourt.infrastructure.driving.http.controller;

import com.pragma.microservicefoodcourt.application.dto.request.CreateRestaurantRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetRestaurantResponse;
import com.pragma.microservicefoodcourt.application.handler.RestaurantHandler;
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

class RestaurantControllerAdapterTest {

    @Mock
    private RestaurantHandler restaurantHandler;

    @InjectMocks
    private RestaurantControllerAdapter restaurantControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create restaurant and return status 201")
    void shouldCreateRestaurantAndReturnStatus201() {
        CreateRestaurantRequest request = CreateRestaurantRequest.builder()
                .name("Test Restaurant")
                .address("Test Address")
                .build();

        ResponseEntity<Void> response = restaurantControllerAdapter.createRestaurant(request);

        verify(restaurantHandler, times(1)).createRestaurant(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Should find all restaurants and return status 200")
    void shouldFindAllRestaurantsAndReturnStatus200() {
        ResponseEntity<List<GetRestaurantResponse>> response = restaurantControllerAdapter.findAllRestaurants(1, 10);

        verify(restaurantHandler, times(1)).findAllRestaurants(1, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}