package com.pragma.microservicefoodcourt.application.handler;

import com.pragma.microservicefoodcourt.application.dto.request.CreateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishRequest;
import com.pragma.microservicefoodcourt.application.mapper.IDishDtoMapper;
import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandler {
    private final IDishServicePort dishServicePort;
    private final IDishDtoMapper dishDtoMapper;

    public void createDish(CreateDishRequest request) {
        dishServicePort.saveDish(dishDtoMapper.toModelFromCreate(request));
    }

    public void updateDish(Long id, UpdateDishRequest request) {
        Dish dish = dishDtoMapper.toModelFromUpdate(request);
        dish.setId(id);
        dishServicePort.updateDish(dish);
    }
}
