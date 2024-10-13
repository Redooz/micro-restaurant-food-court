package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper;

import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.DishEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDishEntityMapper {
    DishEntity toEntity(Dish dish);

    Dish toDomain(DishEntity dishEntity);

    List<Dish> toDomainList(List<DishEntity> dishEntities);
}
