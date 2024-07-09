package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Order;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
}
