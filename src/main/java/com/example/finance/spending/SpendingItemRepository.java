package com.example.finance.spending;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.finance.user.User;

public interface SpendingItemRepository extends JpaRepository<SpendingItem, Long> {

    @Query("SELECT s FROM Spending s WHERE s.user = ?1 AND s.date = ?2")
    List<SpendingItem> getSpendingByUserAndMonth(User user, LocalDate date);

}
