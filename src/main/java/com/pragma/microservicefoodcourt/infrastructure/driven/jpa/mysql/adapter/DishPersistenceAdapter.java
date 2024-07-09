package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter;

import com.pragma.microservicefoodcourt.domain.model.Category;
import com.pragma.microservicefoodcourt.domain.model.Dish;
import com.pragma.microservicefoodcourt.domain.spi.IDishPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.DishEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DishPersistenceAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishMapper;
    private final ICategoryEntityMapper categoryMapper;

    @Override
    public void saveDish(Dish dish) {
        dishRepository.save(dishMapper.toEntity(dish));
    }

    @Override
    public Optional<Dish> findDishById(Long id) {
        return dishRepository.findById(id).map(dishMapper::toDomain);
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dishMapper.toEntity(dish));
    }

    @Override
    public List<Dish> findAllDishes(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<DishEntity> entityDishes = dishRepository.findAll(pageable).getContent();

        return dishMapper.toDomainList(entityDishes);
    }

    @Override
    public List<Dish> findDishesByCategory(Category category, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        CategoryEntity categoryEntity = categoryMapper.toEntity(category);

        List<DishEntity> entityDishes = dishRepository.findAllByCategory(categoryEntity, pageable).getContent();

        return dishMapper.toDomainList(entityDishes);
    }
}
