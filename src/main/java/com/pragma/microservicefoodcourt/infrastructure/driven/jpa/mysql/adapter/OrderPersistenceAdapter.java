package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter;

import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderPersistenceAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }

    @Override
    public List<Order> findAllOrdersByClientId(User user) {
        List<OrderEntity> orderEntities = orderRepository.findAllByClientId(user.getDocumentId());

        return orderEntityMapper.toModelList(orderEntities);
    }
}
