package com.example.service;

import com.example.model.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardService {

    Card createCard(Card card);
    Card getCardById(Long id);
    Card updateCard(Long id, Card card);
    ResponseEntity<?> deleteCard(Long id);
    List<Card> getCardsByClient(Long id);
}
