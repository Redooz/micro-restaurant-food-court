package com.pragma.microservicefoodcourt.application.mapper;

import com.pragma.microservicefoodcourt.application.dto.request.CreateOrderRequest;
import com.pragma.microservicefoodcourt.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "chef", ignore = true)
    @Mapping(target = "client", ignore = true)
    Order toModelFromCreate(CreateOrderRequest createOrderRequest);
}
