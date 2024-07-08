package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.exception.NotOwnerException;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.Role;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;

import java.util.Optional;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserApiPort userApiPort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserApiPort userApiPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userApiPort = userApiPort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        User user = userApiPort.findUserById(restaurant.getOwnerId());

        if (!user.getRole().equals(Role.OWNER)) {
            throw new NotOwnerException(user.getDocumentId());
        }

        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Optional<Restaurant> findRestaurantByNit(String nit) {
        return restaurantPersistencePort.findByNit(nit);
    }
}
