package com.pragma.microservicefoodcourt.application.handler;

import com.pragma.microservicefoodcourt.application.dto.request.CreateOrderRequest;
import com.pragma.microservicefoodcourt.application.mapper.IOrderDtoMapper;
import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IOrderDtoMapper orderDtoMapper;

    public void createOrder(CreateOrderRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderDtoMapper.toModelFromCreate(request);

        order.setClientId(loggedUser.getDocumentId());
        orderServicePort.saveOrder(order);
    }
}
