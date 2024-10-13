package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository;

import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long>{
    Page<DishEntity> findAll(Pageable pageable);
    Page<DishEntity> findAllByCategory(CategoryEntity category, Pageable pageable);
}
