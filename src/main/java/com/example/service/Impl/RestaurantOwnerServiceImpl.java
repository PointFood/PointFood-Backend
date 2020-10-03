package com.example.service.Impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Restaurant;
import com.example.model.RestaurantOwner;
import com.example.repository.RestaurantOwnerRepository;
import com.example.service.RestaurantOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {

    @Autowired
    private RestaurantOwnerRepository restaurantOwnerRepository;

    @Transactional
    @Override
    public RestaurantOwner createRestaurantOwner(RestaurantOwner restaurantOwner) {
        return restaurantOwnerRepository.save(restaurantOwner);
    }

    @Transactional(readOnly = true)
    @Override
    public RestaurantOwner getRestaurantOwnerById(Long id) {
        return restaurantOwnerRepository.findRestaurantOwnerById(id);
    }

    @Transactional
    @Override
    public RestaurantOwner updateRestaurantOwner(Long id, RestaurantOwner restaurantOwner) {
        RestaurantOwner restaurantOwnerDB = restaurantOwnerRepository.getOne(id);
        if(restaurantOwnerDB == null){
            throw new ResourceNotFoundException("There is no restaurant with Id " + id );
        }
        restaurantOwnerDB.setName(restaurantOwner.getName());
        restaurantOwnerDB.setPhone(restaurantOwner.getPhone());
        restaurantOwnerDB.setEmail(restaurantOwner.getEmail());
        restaurantOwnerDB.setUsername(restaurantOwner.getUsername());
        restaurantOwnerDB.setPassword(restaurantOwner.getPassword());

        return restaurantOwnerRepository.save(restaurantOwnerDB);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteRestaurantOwner(Long id) {
        RestaurantOwner restaurantOwnerDB = restaurantOwnerRepository.getOne(id);
        if(restaurantOwnerDB == null){
            throw new ResourceNotFoundException("There is no restaurant with Id " + id );
        }
        restaurantOwnerRepository.delete(restaurantOwnerDB);

        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    @Override
    public RestaurantOwner getRestaurantOwnerByUsernameAndPassword(String username, String password) {
        return restaurantOwnerRepository.findRestaurantOwnerByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    @Override
    public RestaurantOwner getRestaurantOwnerByUsernameAndEmail(String username, String email) {
        return restaurantOwnerRepository.findRestaurantOwnerByUsernameAndEmail(username, email);
    }
}
