package com.pragma.microservicefoodcourt.application.mapper;

import com.pragma.microservicefoodcourt.application.dto.request.CreateOrderRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetOrderResponse;
import com.pragma.microservicefoodcourt.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "chefId", ignore = true)
    @Mapping(target = "clientId", ignore = true)
    Order toModelFromCreate(CreateOrderRequest createOrderRequest);

    GetOrderResponse toResponse(Order order);

    List<GetOrderResponse> toResponseList(List<Order> orders);
}
