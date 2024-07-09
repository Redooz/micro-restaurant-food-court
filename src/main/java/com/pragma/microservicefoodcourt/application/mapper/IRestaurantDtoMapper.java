package com.pragma.microservicefoodcourt.application.mapper;

import com.pragma.microservicefoodcourt.application.dto.request.CreateRestaurantRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetRestaurantResponse;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRestaurantDtoMapper {
    Restaurant toModel(CreateRestaurantRequest restaurantDto);

    GetRestaurantResponse toResponse(Restaurant restaurant);

    List<GetRestaurantResponse> toResponseList(List<Restaurant> restaurants);
}
