package com.example.finance.repositories;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cglib.core.Local;

import com.example.finance.entities.SpendingItem;
import com.example.finance.entities.User;

@DataJpaTest
public class SpendingItemRepositoryTest {

    @Autowired
    SpendingItemRepository spendingItemRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void testGetSpendingByUserAndMonth() {
        User issac = new User("Issac",    "Newton", "inewt23", "pass1234", "inewt12@gmail.com");
        User polly = new User("Polly",    "Hashton", "polly56", "pass1234", "polly12@gmail.com");

        userRepository.saveAll(List.of(issac,polly));

        LocalDate testDate = LocalDate.of(2024, 9, 12);
        spendingItemRepository.saveAll(List.of(
            new SpendingItem(issac, null, "nike shoes", 308.98, LocalDate.of(2024, 10, 12)),
            new SpendingItem(issac, null, "batteries", 30.00,testDate),
            new SpendingItem(issac, null, "filters", 8.98, LocalDate.of(2024, 10, 12)),
            new SpendingItem(issac, null, "rent", 1308.98, testDate),
            new SpendingItem(polly, null, "rent", 1308.98, testDate)
        ));
        List<SpendingItem> items = spendingItemRepository.getSpendingByUserAndYearAndMonth(issac, testDate.getYear(), testDate.getMonthValue());

        System.out.println(items);
        Assertions.assertTrue(items.size() == 2);
    }
}
