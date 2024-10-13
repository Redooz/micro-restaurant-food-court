package com.pragma.microservicefoodcourt.application.handler;

import com.pragma.microservicefoodcourt.application.dto.request.CreateRestaurantRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetRestaurantResponse;
import com.pragma.microservicefoodcourt.application.mapper.IRestaurantDtoMapper;
import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantDtoMapper restaurantDtoMapper;

    public void createRestaurant(CreateRestaurantRequest request) {
        restaurantServicePort.saveRestaurant(restaurantDtoMapper.toModel(request));
    }

    public List<GetRestaurantResponse> findAllRestaurants(int page, int size) {
        return restaurantDtoMapper.toResponseList(restaurantServicePort.findAllRestaurants(page, size));
    }
}
