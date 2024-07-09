package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper;

import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toDomain(CategoryEntity categoryEntity);

    List<Category> toDomainList(List<CategoryEntity> categoryEntities);
}
