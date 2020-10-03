package com.example.repository;

import com.example.model.DishExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishExtraRepository extends JpaRepository<DishExtra, Long> {
}
