package com.example.finance.budget;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.finance.user.User;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    //JPQL allows you to interact with java entities vs traditional tables and colums
    @Query("SELECT b FROM Budget b WHERE b.user = ?1")
    List<Budget> findBudgetByUser(User user);

    @Query("SELECT b FROM Budget b WHERE b.user = ?1 AND b.date = ?2")
    Optional<Budget> findBudgetByUserAndDate(User user, LocalDate date);

}
