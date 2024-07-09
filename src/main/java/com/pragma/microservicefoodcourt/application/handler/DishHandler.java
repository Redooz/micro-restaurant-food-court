package com.pragma.microservicefoodcourt.application.handler;

import com.pragma.microservicefoodcourt.application.dto.request.CreateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishRequest;
import com.pragma.microservicefoodcourt.application.dto.request.UpdateDishStatusRequest;
import com.pragma.microservicefoodcourt.application.mapper.IDishDtoMapper;
import com.pragma.microservicefoodcourt.domain.api.IDishServicePort;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandler {
    private final IDishServicePort dishServicePort;
    private final IDishDtoMapper dishDtoMapper;

    public void createDish(CreateDishRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        dishServicePort.saveDish(dishDtoMapper.toModelFromCreate(request), loggedUser);
    }

    public void updateDish(Long id, UpdateDishRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Dish dish = dishDtoMapper.toModelFromUpdate(request);
        dish.setId(id);
        dishServicePort.updateDish(dish, loggedUser);
    }

    public void updateDishStatus(Long id, UpdateDishStatusRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        dishServicePort.updateActiveStatus(id, request.status(), loggedUser);
    }
}
