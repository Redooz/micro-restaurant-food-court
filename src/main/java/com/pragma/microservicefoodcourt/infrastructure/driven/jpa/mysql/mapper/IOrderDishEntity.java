package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper;

import com.pragma.microservicefoodcourt.domain.model.OrderDish;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.OrderDishEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderDishEntity {
    OrderDishEntity toModel(OrderDish orderDish);

    OrderDish toEntity(OrderDishEntity orderDishEntity);

    List<OrderDish> toModelList(List<OrderDishEntity> orderDishEntities);
}
