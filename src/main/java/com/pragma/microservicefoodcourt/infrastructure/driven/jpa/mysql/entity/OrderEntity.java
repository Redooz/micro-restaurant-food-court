package com.pragma.microservicefoodcourt.infrastructure.driven.jpa.mysql.entity;

import com.pragma.microservicefoodcourt.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "restaurant_nit")
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "orderId")
    private List<OrderDishEntity> orderDishes;

    private String chefId;

    private String clientId;
}
