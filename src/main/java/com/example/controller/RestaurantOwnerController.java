package com.example.controller;


import com.example.model.Client;
import com.example.model.RestaurantOwner;
import com.example.service.RestaurantOwnerService;
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
@RequestMapping("/restaurant-owners")
public class RestaurantOwnerController {

    @Autowired
    private RestaurantOwnerService restaurantOwnerService;

    @PostMapping
    public ResponseEntity<RestaurantOwner> postRestaurantOwner(@Valid @RequestBody RestaurantOwner restaurantOwner, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        RestaurantOwner restaurantOwnerDB = restaurantOwnerService.createRestaurantOwner(restaurantOwner);

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerDB);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantOwner> getRestaurantOwner(@PathVariable("id")Long id){
        RestaurantOwner restaurantOwnerDB = restaurantOwnerService.getRestaurantOwnerById(id);
        if(restaurantOwnerDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurantOwnerDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantOwner> updateRestaurantOwner(@PathVariable("id") Long id,
                                                                 @RequestBody RestaurantOwner restaurantOwner) {
        RestaurantOwner restaurantOwnerDB = restaurantOwnerService.getRestaurantOwnerById(id);
        if(restaurantOwnerDB == null){
            return ResponseEntity.notFound().build();
        }
        restaurantOwner.setId(id);
        restaurantOwnerDB = restaurantOwnerService.updateRestaurantOwner(id, restaurantOwner);

        return ResponseEntity.ok(restaurantOwnerDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurantOwner(@PathVariable("id") Long id) {
        RestaurantOwner restaurantOwnerDB = restaurantOwnerService.getRestaurantOwnerById(id);
        if(restaurantOwnerDB == null){
            return ResponseEntity.notFound().build();
        }

        return restaurantOwnerService.deleteRestaurantOwner(id);
    }

    @GetMapping("/login")
    public ResponseEntity<RestaurantOwner> login(@RequestBody RestaurantOwner restaurantOwner) {
        RestaurantOwner restaurantOwnerDB = restaurantOwnerService
                .getRestaurantOwnerByUsernameAndPassword(restaurantOwner.getUsername(), restaurantOwner.getPassword());
        if(restaurantOwnerDB == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurantOwnerDB);
    }

    @GetMapping("/recover")
    public ResponseEntity<RestaurantOwner> recoverAccount(@RequestBody RestaurantOwner restaurantOwner) {
        RestaurantOwner restaurantOwnerDB = restaurantOwnerService
                .getRestaurantOwnerByUsernameAndEmail(restaurantOwner.getUsername(), restaurantOwner.getEmail());
        if(restaurantOwnerDB == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurantOwnerDB);
    }
}
