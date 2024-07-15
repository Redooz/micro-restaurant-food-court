package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    List<Order> findAllOrdersByClientId(User user);
    List<Order> findAllOrdersByStatusAndRestaurant(OrderStatus status, Restaurant restaurant, int page, int size);
    Optional<Order> findOrderById(Long orderId);
    void updateOrder(Order order);
}
