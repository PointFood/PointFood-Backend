package com.example.controller;

import com.example.model.Card;
import com.example.service.CardService;
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
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> postCard(@Valid @RequestBody Card card, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Card cardDB = cardService.createCard(card);

        return ResponseEntity.status(HttpStatus.CREATED).body(cardDB);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable("id")Long id){
        Card cardDB = cardService.getCardById(id);
        if(cardDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cardDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable("id") Long id, @RequestBody Card card){
        Card cardDB = cardService.getCardById(id);
        if(cardDB == null) {
            return ResponseEntity.notFound().build();
        }
        card.setId(id);
        cardDB = cardService.updateCard(id, card);

        return ResponseEntity.ok(cardDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id") Long id){
        Card cardDB = cardService.getCardById(id);
        if(cardDB == null){
            return ResponseEntity.notFound().build();
        }

        return cardService.deleteCard(id);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<List<Card>>listCardsByClient(@PathVariable("id") Long id){
        List<Card> cards = cardService.getCardsByClient(id);
        if (cards.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cards);
    }
}
