package com.example.finance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.finance.entities.User;

//Responsible to for interactiong with the database
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM Users u WHERE u.email = ?1") //JPQL
    Optional<User> findUserByEmail(String email);

}
