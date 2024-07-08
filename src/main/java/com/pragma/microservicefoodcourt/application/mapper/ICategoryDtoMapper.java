package com.pragma.microservicefoodcourt.application.mapper;

import com.pragma.microservicefoodcourt.application.dto.request.CreateCategoryRequest;
import com.pragma.microservicefoodcourt.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryDtoMapper {
    @Mapping(target = "id", ignore = true)
    Category toModel(CreateCategoryRequest createCategoryRequest);
}
