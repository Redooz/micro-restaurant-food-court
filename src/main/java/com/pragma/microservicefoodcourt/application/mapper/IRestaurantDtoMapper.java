package com.pragma.microservicefoodcourt.application.mapper;

import com.pragma.microservicefoodcourt.application.dto.request.CreateRestaurantRequest;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRestaurantDtoMapper {
    Restaurant toModel(CreateRestaurantRequest restaurantDto);
}
