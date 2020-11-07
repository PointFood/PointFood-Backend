package com.example.controller;


import com.example.model.Manager;
import com.example.service.ManagerService;
import com.example.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping
    public ResponseEntity<Manager> postRestaurantOwner(@Valid @RequestBody Manager manager, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Manager managerDB = managerService.createRestaurantOwner(manager);

        return ResponseEntity.status(HttpStatus.CREATED).body(managerDB);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getRestaurantOwner(@PathVariable("id")Long id){
        Manager managerDB = managerService.getRestaurantOwnerById(id);
        if(managerDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(managerDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateRestaurantOwner(@PathVariable("id") Long id,
                                                         @RequestBody Manager manager) {
        Manager managerDB = managerService.getRestaurantOwnerById(id);
        if(managerDB == null){
            return ResponseEntity.notFound().build();
        }
        manager.setId(id);
        managerDB = managerService.updateRestaurantOwner(id, manager);

        return ResponseEntity.ok(managerDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurantOwner(@PathVariable("id") Long id) {
        Manager managerDB = managerService.getRestaurantOwnerById(id);
        if(managerDB == null){
            return ResponseEntity.notFound().build();
        }

        return managerService.deleteRestaurantOwner(id);
    }

    @GetMapping("/login")
    public ResponseEntity<Manager> login(@RequestBody Manager manager) {
        Manager managerDB = managerService
                .getRestaurantOwnerByUsernameAndPassword(manager.getUsername(), manager.getPassword());
        if(managerDB == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(managerDB);
    }

    @GetMapping("/recover")
    public ResponseEntity<Manager> recoverAccount(@RequestBody Manager manager) {
        Manager managerDB = managerService
                .getRestaurantOwnerByUsernameAndEmail(manager.getUsername(), manager.getEmail());
        if(managerDB == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(managerDB);
    }
}
