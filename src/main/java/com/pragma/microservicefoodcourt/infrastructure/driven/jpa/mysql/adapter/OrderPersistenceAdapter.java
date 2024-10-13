package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.adapter;

import com.pragma.microservicefoodcourt.domain.model.Order;
import com.pragma.microservicefoodcourt.domain.model.enums.OrderStatus;
import com.pragma.microservicefoodcourt.domain.model.Restaurant;
import com.pragma.microservicefoodcourt.domain.model.User;
import com.pragma.microservicefoodcourt.domain.spi.IOrderPersistencePort;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IOrderDishRepository;
import com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderPersistenceAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity savedOrder = orderRepository.save(orderEntityMapper.toEntity(order));

        order.getOrderDishes().forEach(orderDish ->
                orderDish.setOrderId(savedOrder.getId())
        );

        List<OrderDishEntity> orderDishEntities = orderEntityMapper.orderDishListToEntityList(order.getOrderDishes());

        orderDishRepository.saveAll(orderDishEntities);

        return orderEntityMapper.toModel(savedOrder);
    }

    @Override
    public List<Order> findAllOrdersByClientId(User user) {
        List<OrderEntity> orderEntities = orderRepository.findAllByClientId(user.getDocumentId());

        return orderEntityMapper.toModelList(orderEntities);
    }

    @Override
    public List<Order> findAllOrdersByStatusAndRestaurant(OrderStatus status, Restaurant restaurant, int page, int size) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<OrderEntity> orderEntities = orderRepository.findAllByStatusAndRestaurant(status, restaurantEntity, pageable).getContent();

        return orderEntityMapper.toModelList(orderEntities);
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        return  orderRepository.findById(orderId).map(orderEntityMapper::toModel);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }
}
