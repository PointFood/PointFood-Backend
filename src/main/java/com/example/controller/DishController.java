package com.example.controller;

import com.example.model.Card;
import com.example.model.Client;
import com.example.model.Dish;
import com.example.repository.DishRepository;
import com.example.service.DishService;
import com.example.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<Dish> postDish(@Valid @RequestBody Dish dish, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Dish dishDB = dishService.createDish(dish);

        return ResponseEntity.status(HttpStatus.CREATED).body(dishDB);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDish(@PathVariable("id")Long id){
        Dish dishDB =  dishService.getDishById(id);
        if(dishDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dishDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable("id") long id, @RequestBody Dish dish){
        Dish dishDB =  dishService.getDishById(id);
        if(dishDB == null) {
            return ResponseEntity.notFound().build();
        }
        dish.setId(id);
        dishDB = dishService.updateDish(id, dish);

        return ResponseEntity.ok(dishDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id){
        Dish dishDB =  dishService.getDishById(id);
        if(dishDB == null) {
            return ResponseEntity.notFound().build();
        }

        return dishService.deleteDish(id);
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<List<Dish>>listDishesByRestaurant(@PathVariable("id") Long id){
        List<Dish> dishes = dishService.getDishesByRestaurant(id);
        if (dishes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dishes);
    }
}
