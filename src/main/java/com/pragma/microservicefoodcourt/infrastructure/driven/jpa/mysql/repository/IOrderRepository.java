package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository;

import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByClientId(String clientId);
    Page<OrderEntity> findAllByStatusAndRestaurant(OrderStatus status, RestaurantEntity restaurant, Pageable pageable);
}
