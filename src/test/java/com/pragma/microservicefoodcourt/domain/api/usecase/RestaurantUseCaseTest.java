package com.pragma.microservicefoodcourt.domain.api.usecase;

import com.pragma.microservicefoodcourt.domain.builder.RestaurantBuilder;
import com.pragma.microservicefoodcourt.domain.builder.UserBuilder;
import com.pragma.microservicefoodcourt.domain.exception.NotOwnerException;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.Role;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.microservicefoodcourt.domain.spi.IUserApiPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserApiPort userApiPort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save restaurant when user is owner")
    void shouldSaveRestaurantWhenUserIsOwner() {
        UserBuilder userBuilder = new UserBuilder();
        User owner = userBuilder.setRole(Role.OWNER).createUser();

        RestaurantBuilder restaurantBuilder = new RestaurantBuilder();
        Restaurant restaurant = restaurantBuilder.setOwnerId("ownerId").createRestaurant();

        when(userApiPort.getUserById("ownerId")).thenReturn(owner);

        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistencePort, times(1)).saveRestaurant(restaurant);
    }

    @Test
    @DisplayName("Should throw exception when user is not owner")
    void shouldThrowExceptionWhenUserIsNotOwner() {
        Restaurant restaurant = new RestaurantBuilder().setOwnerId("1L").createRestaurant();

        User user = new UserBuilder().setRole(Role.EMPLOYEE).setDocumentId("nonOwnerDocId").createUser();

        when(userApiPort.getUserById(restaurant.getOwnerId())).thenReturn(user);

        assertThrows(
                NotOwnerException.class,
                () -> restaurantUseCase.saveRestaurant(restaurant)
        );
        verify(userApiPort).getUserById(restaurant.getOwnerId());
        verify(restaurantPersistencePort, never()).saveRestaurant(restaurant);
    }
}
