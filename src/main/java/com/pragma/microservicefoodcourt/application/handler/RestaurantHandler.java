package com.pragma.microservicefoodcourt.application.handler;

import com.pragma.microservicefoodcourt.application.dto.request.CreateRestaurantRequest;
import com.pragma.microservicefoodcourt.application.mapper.IRestaurantDtoMapper;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantDtoMapper restaurantDtoMapper;

    public void createRestaurant(CreateRestaurantRequest request) {
        restaurantServicePort.saveRestaurant(restaurantDtoMapper.toModel(request));
    }
}
