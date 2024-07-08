package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository;

import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long>{
}
