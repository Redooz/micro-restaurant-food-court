package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper;

import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.RestaurantEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurantEntity);

    Restaurant toModel(RestaurantEntity restaurant);

    List<Restaurant> toModelList(List<RestaurantEntity> restaurantEntities);
}
