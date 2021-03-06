package com.example.service;

import com.example.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    Order getOrderById(Long id);
    Order updateOrderState(Long id);
    List<Order> getOrdersByRestaurant(Long id);
}
