package com.example.controller;

import com.example.model.State;
import com.example.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> listStates(){
        List<State> states = stateService.getStates();
        if(states.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(states);
    }
}
