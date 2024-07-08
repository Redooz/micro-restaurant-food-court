package com.pragma.microservicefoodcourt.domain.api;

import com.pragma.microservicefoodcourt.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    Optional<Restaurant> findRestaurantByNit(String nit);
}
