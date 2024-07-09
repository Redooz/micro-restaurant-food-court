package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.api.IRestaurantServicePort;
import com.pragma.microservicefoodcourt.domain.constant.RestaurantConstant;
import com.pragma.microservicefoodcourt.domain.exception.NoDataFoundException;
import com.pragma.microservicefoodcourt.domain.exception.NotOwnerException;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.Role;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;

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
    public Restaurant findRestaurantByNit(String nit) {
        return restaurantPersistencePort.findByNit(nit).orElseThrow(() -> new NoDataFoundException(
                String.format(RestaurantConstant.RESTAURANT_NOT_FOUND_EXCEPTION_MESSAGE, nit)
        ));
    }
}
