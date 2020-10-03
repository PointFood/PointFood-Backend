package com.example.repository;

import com.example.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("SELECT di FROM Dish di WHERE di.restaurant.id=?1")
    List<Dish> findDishesByRestaurant(Long id);

    Dish findDishById(Long id);
}
