package com.example.service;

import com.example.model.Manager;
import org.springframework.http.ResponseEntity;

public interface ManagerService {

    Manager createRestaurantOwner(Manager manager);
    Manager getRestaurantOwnerById(Long id);
    Manager updateRestaurantOwner(Long id, Manager manager);
    ResponseEntity<?> deleteRestaurantOwner(Long id);
    Manager getRestaurantOwnerByUsernameAndPassword(String username, String password);
    Manager getRestaurantOwnerByUsernameAndEmail(String username, String email);
}
