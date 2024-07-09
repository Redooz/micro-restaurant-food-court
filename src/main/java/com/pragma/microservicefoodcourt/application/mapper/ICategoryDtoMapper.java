package com.pragma.microservicefoodcourt.application.mapper;

import com.pragma.microservicefoodcourt.application.dto.request.CreateCategoryRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetCategoryResponse;
import com.pragma.microservicefoodcourt.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryDtoMapper {
    @Mapping(target = "id", ignore = true)
    Category toModel(CreateCategoryRequest createCategoryRequest);

    GetCategoryResponse toResponse(Category category);

    List<GetCategoryResponse> toResponseList(List<Category> categories);
}
