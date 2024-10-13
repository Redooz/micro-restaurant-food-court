package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository;

import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, Long> {
}
