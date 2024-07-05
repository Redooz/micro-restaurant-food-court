package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter;

import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IRestaurantRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RestaurantPersistenceAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        return restaurantRepository.findByName(name).map(restaurantEntityMapper::toModel);
    }

    @Override
    public Optional<Restaurant> findByNit(String nit) {
        return restaurantRepository.findById(nit).map(restaurantEntityMapper::toModel);
    }
}
