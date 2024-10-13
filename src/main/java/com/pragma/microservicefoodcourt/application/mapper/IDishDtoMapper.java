package com.pragma.microservicefoodcourt.application.mapper;

import com.pragma.microservicefoodcourt.application.dto.request.CreateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetDishResponse;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDishDtoMapper {
    @Mapping(target = "id", ignore = true)
    Dish toModelFromCreate(CreateDishRequest createDishRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "urlImage", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Dish toModelFromUpdate(UpdateDishRequest updateDishRequest);

    GetDishResponse toResponseFromDish(Dish dish);

    List<GetDishResponse> toResponseList(List<Dish> dishes);
}
