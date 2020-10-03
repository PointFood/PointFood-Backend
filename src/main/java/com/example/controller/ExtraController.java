package com.example.controller;

import com.example.model.Extra;
import com.example.service.ExtraService;
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
@RequestMapping("/extras")
public class ExtraController {

    @Autowired
    private ExtraService extraService;

    @PostMapping
    public ResponseEntity<Extra> postExtra(@Valid @RequestBody Extra extra, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Extra extraDB = extraService.createExtra(extra);

        return ResponseEntity.status(HttpStatus.CREATED).body(extraDB);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Extra> getExtra(@PathVariable("id")Long id){
        Extra extraDB =  extraService.getExtraById(id);
        if(extraDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(extraDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Extra> updateExtra(@PathVariable("id") long id, @RequestBody Extra extra){
        Extra extraDB =  extraService.getExtraById(id);
        if(extraDB == null){
            return ResponseEntity.notFound().build();
        }
        extra.setId(id);
        extraDB = extraService.updateExtra(id, extra);

        return ResponseEntity.ok(extraDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExtra(@PathVariable("id") Long id){
        Extra extraDB =  extraService.getExtraById(id);
        if(extraDB == null){
            return ResponseEntity.notFound().build();
        }

        return extraService.deleteExtra(id);
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<List<Extra>>listExtrasByRestaurant(@PathVariable("id") Long id){
        List<Extra> extras = extraService.getExtrasByRestaurant(id);
        if (extras.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(extras);
    }
}
