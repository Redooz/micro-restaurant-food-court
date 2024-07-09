package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.model.User;

public interface IDishServicePort {
    void saveDish(Dish dish, User loggedUser);
    void updateDish(Dish dish, User loggedUser);

}
