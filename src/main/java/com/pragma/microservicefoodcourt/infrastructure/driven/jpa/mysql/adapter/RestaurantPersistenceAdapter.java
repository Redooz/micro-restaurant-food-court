package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter;

import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IRestaurantRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
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

    @Override
    public List<Restaurant> findAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll(pageable).getContent();

        return restaurantEntityMapper.toModelList(restaurantEntities);
    }
}
