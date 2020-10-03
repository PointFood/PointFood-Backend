package com.example.service.Impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Card;
import com.example.repository.CardRepository;
import com.example.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Transactional(readOnly = true)
    @Override
    public Card getCardById(Long id) {
        Card cardDB=cardRepository.findCardById(id);
        if(cardDB==null){
            throw new ResourceNotFoundException("There is no card with Id " + id);
        }

        return cardDB;
    }

    @Transactional
    @Override
    public Card updateCard(Long id, Card card) {
        Card cardDB = cardRepository.getOne(id);
        if(cardDB == null){
            throw new ResourceNotFoundException("There is no card with Id " + id);
        }
        cardDB.setNumber(card.getNumber());

        return cardRepository.save(cardDB);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteCard(Long id) {
        Card cardDB = cardRepository.getOne(id);
        if (cardDB == null){
            throw  new ResourceNotFoundException("There is no Card with Id " + id);
        }
        cardRepository.delete(cardDB);

        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Card> getCardsByClient(Long id) {
        return cardRepository.findCardsByClient(id);
    }
}
