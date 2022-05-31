package com.tabram.coffeemaker.repository;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeAdminRepository extends JpaRepository<CoffeeAdmin, Long> {


    @Query("SELECT c FROM CoffeeAdmin c WHERE c.nameOfCoffee = ?1")
    Optional<CoffeeAdmin> findCoffeeByName(String nameOfCoffee);


}
