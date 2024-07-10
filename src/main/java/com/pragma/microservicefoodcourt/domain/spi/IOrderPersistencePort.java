package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.User;

import java.util.List;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
    List<Order> findAllOrdersByClientId(User user);
}
