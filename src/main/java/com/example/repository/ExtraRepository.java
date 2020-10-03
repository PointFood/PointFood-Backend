package com.example.repository;

import com.example.model.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Long> {

    @Query("SELECT ex FROM Extra ex WHERE ex.restaurant.id=?1")
    List<Extra> findExtrasByRestaurant(Long id);

    Extra findExtraById(Long id);
}
