package com.example.repository;

import com.example.model.Client;
import com.example.model.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, Long> {

    @Query("SELECT ro FROM RestaurantOwner ro WHERE ro.username=?1 and ro.password=?2")
    RestaurantOwner findRestaurantOwnerByUsernameAndPassword(String username, String password);

    @Query("SELECT ro FROM RestaurantOwner ro WHERE ro.username=?1 and ro.email=?2")
    RestaurantOwner findRestaurantOwnerByUsernameAndEmail(String username, String email);

    RestaurantOwner findRestaurantOwnerById(Long id);
}
