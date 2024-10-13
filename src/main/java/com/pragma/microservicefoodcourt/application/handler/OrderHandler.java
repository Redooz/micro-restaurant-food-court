package com.pragma.microservicefoodcourt.application.handler;

import com.pragma.microservicefoodcourt.application.dto.request.CreateOrderRequest;
import com.pragma.microservicefoodcourt.application.dto.request.DeliverOrderRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetOrderResponse;
import com.pragma.microservicefoodcourt.application.mapper.IOrderDtoMapper;
import com.pragma.microservicefoodcourt.domain.api.IOrderServicePort;
import com.pragma.microservicefoodcourt.domain.builder.RestaurantBuilder;
import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<GetOrderResponse> findAllOrdersByStatusAndRestaurant(String nit, OrderStatus status, int page, int size) {
        User loggedEmployee = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Restaurant restaurant = new RestaurantBuilder().setNit(nit).createRestaurant();

        return orderDtoMapper.toResponseList(orderServicePort.findAllOrdersByStatusAndRestaurant(
                loggedEmployee, status, restaurant, page, size
        ));
    }

    public void assignOrderToEmployee(Long orderId) {
        User loggedEmployee = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderServicePort.assignOrderToEmployee(loggedEmployee, orderId);
    }

    public void finishOrder(Long orderId) {
        User loggedEmployee = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderServicePort.finishOrder(loggedEmployee, orderId);
    }

    public void deliverOrder(Long orderId, DeliverOrderRequest request) {
        User loggedEmployee = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderServicePort.deliverOrder(loggedEmployee, orderId, request.code());
    }

    public void cancelOrder(Long orderId) {
        User loggedCustomer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderServicePort.cancelOrder(loggedCustomer, orderId);
    }

}
