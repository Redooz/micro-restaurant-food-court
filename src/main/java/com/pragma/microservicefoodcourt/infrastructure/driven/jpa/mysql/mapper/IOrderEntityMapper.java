package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper;

import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderEntityMapper {
    OrderEntity toEntity(Order order);

    Order toModel(OrderEntity orderEntity);

    List<Order> toModelList(List<OrderEntity> orderEntities);
}
