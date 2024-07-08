package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.Dish;

public interface IDishServicePort {
    void saveDish(Dish dish);
}
