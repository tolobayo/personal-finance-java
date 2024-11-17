package com.example.finance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.finance.entities.SpendingItem;
import com.example.finance.entities.User;

public interface SpendingItemRepository extends JpaRepository<SpendingItem, Long> {

    @Query("SELECT s FROM Spending s WHERE s.user = ?1 AND YEAR(s.date) = ?2 AND MONTH(s.date) = ?3")
    List<SpendingItem> getSpendingByUserAndYearAndMonth(User user, int year, int month);


}
