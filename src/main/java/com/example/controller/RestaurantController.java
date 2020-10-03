package com.example.controller;
import com.example.model.Restaurant;
import com.example.service.RestaurantService;
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
    @RequestMapping("/restaurants")
    public class RestaurantController
    {
        @Autowired
        private RestaurantService restaurantService;

        @PostMapping
        public ResponseEntity<Restaurant> postRestaurant(@Valid @RequestBody Restaurant restaurant, BindingResult result){
            if(result.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
            }
            Restaurant restaurantDB = restaurantService.createRestaurant(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurantDB);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id")Long id){
            Restaurant restaurantDB =  restaurantService.getRestaurantById(id);
            if(restaurantDB == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(restaurantDB);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") long id, @RequestBody Restaurant restaurant){
            Restaurant restaurantDB =  restaurantService.getRestaurantById(id);
            if(restaurantDB == null){
                return ResponseEntity.notFound().build();
            }
            restaurant.setId(id);
            restaurantDB = restaurantService.updateRestaurant(id, restaurant);

            return ResponseEntity.ok(restaurantDB);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteRestaurant(@PathVariable("id") Long id){
            Restaurant restaurantDB =  restaurantService.getRestaurantById(id);
            if(restaurantDB == null){
                return ResponseEntity.notFound().build();
            }

            return restaurantService.deleteRestaurant(id);
        }

        @GetMapping
        public ResponseEntity<List<Restaurant>>listRestaurants(){
            List<Restaurant> restaurants = restaurantService.getRestaurants();
            if (restaurants.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(restaurants);
        }
    }
