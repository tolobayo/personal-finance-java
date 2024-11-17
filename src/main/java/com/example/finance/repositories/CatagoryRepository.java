package com.example.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.finance.entities.Catagory;
import com.example.finance.entities.User;

public interface CatagoryRepository extends JpaRepository<Catagory, Long> {

    List<Catagory> findByUser(User user);

    Optional<Catagory> findByName(String name);

}
