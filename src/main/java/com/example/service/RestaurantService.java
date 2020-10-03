package com.example.service;

import com.example.model.Restaurant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(Long id);
    Restaurant updateRestaurant(Long id, Restaurant restaurant);
    ResponseEntity<?> deleteRestaurant(Long id);
    List<Restaurant> getRestaurants();
}
