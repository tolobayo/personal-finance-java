package com.example.finance.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Responsible to for interactiong with the database
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM Users u WHERE u.email = ?1") //JPQL
    Optional<User> findUserByEmail(String email);

}
