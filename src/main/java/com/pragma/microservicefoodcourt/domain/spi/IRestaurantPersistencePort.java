package com.pragma.microservicefoodcourt.domain.spi;

import com.pragma.microservicefoodcourt.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Optional<Restaurant> findByName(String name);
    Optional<Restaurant> findByNit(String nit);
    List<Restaurant> findAll(int page, int size);
}
