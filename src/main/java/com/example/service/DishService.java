package com.example.service;

import com.example.model.Dish;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishService {

    Dish createDish(Dish dish);
    Dish getDishById(Long id);
    Dish updateDish(Long id, Dish dish);
    ResponseEntity<?> deleteDish(Long id);
    List<Dish> getDishesByRestaurant(Long id);
}
