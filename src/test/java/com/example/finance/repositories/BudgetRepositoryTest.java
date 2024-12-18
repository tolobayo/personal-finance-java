package com.example.finance.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.finance.entities.Budget;
import com.example.finance.entities.Catagory;
import com.example.finance.entities.User;

@DataJpaTest
public class BudgetRepositoryTest {
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private Budget budget1;
    private Budget budget2;

    @BeforeEach
    void setUp() {
        // Initialize test data
        testUser = new User("John", "Doe", "jd21", "password1234", "jd21@gmail.com");
        testUser = userRepository.save(testUser);

        budget1 = new Budget(testUser, new HashMap<Catagory, Double>(), LocalDate.of(2024, 11, 1), 5000.00);
    

        budget2 = new Budget(testUser, new HashMap<Catagory, Double>(), LocalDate.of(2024, 10, 15), 1000.00 );
        
        // Save test data to repository
        budgetRepository.save(budget1);
        budgetRepository.save(budget2);
    }

    @Test
    void testFindBudgetByUser() {
        // Act
        List<Budget> budgets = budgetRepository.findBudgetByUser(testUser);

        // Assert
        assertEquals(2, budgets.size(), "The size of the returned list should be 2");
        assertTrue(budgets.contains(budget1), "The list should contain budget1");
        assertTrue(budgets.contains(budget2), "The list should contain budget2");

    }

    @Test
    void testFindBudgetByUserAndDate() {
         // Act
         Optional<Budget> budget = budgetRepository.findBudgetByUserAndDate(testUser, LocalDate.of(2024, 11, 1));

         // Assert
         assertTrue(budget.isPresent(), "The budget should be present");
         assertEquals(budget1, budget.get(), "The retrieved budget should match budget1");
 
    }

    @Test
    void testFindBudgetByUserAndDate_NotFound() {
        // Act
        Optional<Budget> budget = budgetRepository.findBudgetByUserAndDate(testUser, LocalDate.of(2024, 12, 1));

        // Assert
        assertFalse(budget.isPresent(), "The budget should not be present for this date");

    }
}
