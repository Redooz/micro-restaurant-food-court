package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository;

import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
