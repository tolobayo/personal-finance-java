package com.example.finance.config;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.finance.entities.Budget;
import com.example.finance.entities.Catagory;
import com.example.finance.entities.User;
import com.example.finance.repositories.BudgetRepository;
import com.example.finance.repositories.CatagoryRepository;
import com.example.finance.repositories.UserRepository;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, CatagoryRepository catagoryRepository, BudgetRepository budgetRepository) {
        return args -> {
            User joe = new User("Joe", "Robinson", "jrob23", "password123", "jrob23@gmail.com");
            User molly = new User("Molly", "Robinson", "mrob23", "password123", "mrob23@gmail.com");

            joe = userRepository.save(joe);
            molly = userRepository.save(molly);

            Catagory rent = new Catagory(joe, "Rent");
            Catagory groceries = new Catagory(joe, "Groceries");
            Catagory health = new Catagory(joe, "Health and Fitness");

            rent = catagoryRepository.save(rent);
            groceries = catagoryRepository.save(groceries);
            health = catagoryRepository.save(health);

            Map<Catagory, Double> categoryMap = new HashMap<>();
            categoryMap.put(rent, 1500.00);
            categoryMap.put(groceries, 900.00);
            categoryMap.put(health, 500.00);

            Budget septemberBudet = new Budget(joe, categoryMap, LocalDate.of(2024, 9, 15), 3214.00);

            budgetRepository.save(septemberBudet);

        };
    }
}
