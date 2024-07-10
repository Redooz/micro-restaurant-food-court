package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.Order;

public interface IOrderServicePort {
    void saveOrder(Order order);
}
