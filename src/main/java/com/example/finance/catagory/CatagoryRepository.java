package com.example.finance.catagory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.finance.user.User;

public interface CatagoryRepository extends JpaRepository<Catagory, Long> {

    @Query("SELECT c FROM Catagory c WHERE c.user = ?1")
    List<Catagory> getCatagoriesByUser(User user);

    @Query("SELECT c FROM Catagory c WHERE c.name = ?1")
    Optional<Catagory> findCatagoryByName(String name);

}
