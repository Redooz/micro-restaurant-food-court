package com.pragma.microservicefoodcourt.application.handler;

import com.pragma.microservicefoodcourt.application.dto.request.CreateCategoryRequest;
import com.pragma.microservicefoodcourt.application.dto.response.GetCategoryResponse;
import com.pragma.microservicefoodcourt.application.mapper.ICategoryDtoMapper;
import com.pragma.microservicefoodcourt.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryDtoMapper;

    public void createCategory(CreateCategoryRequest request) {
        categoryServicePort.saveCategory(categoryDtoMapper.toModel(request));
    }

    public List<GetCategoryResponse> findAllCategories(int page, int size) {
        return categoryDtoMapper.toResponseList(categoryServicePort.findAllCategories(page, size));
    }
}
