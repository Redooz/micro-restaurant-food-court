package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.User;

import java.util.List;

public interface IOrderServicePort {
    void saveOrder(Order order);
    List<Order> findAllOrdersByStatusAndRestaurant(User loggedEmployee, OrderStatus status, Restaurant restaurant, int page, int size);
    Order findOrderById(Long orderId);
    void assignOrderToEmployee(User loggedEmployee, Long orderId);
    void finishOrder(User loggedEmployee, Long orderId);
}
