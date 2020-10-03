package com.example.repository;

import com.example.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT ca FROM Card ca WHERE ca.id=?1")
    Card findCardById(Long id);

    @Query("SELECT ca FROM Card ca WHERE ca.client.id=?1")
    List<Card> findCardsByClient(Long id);
}
