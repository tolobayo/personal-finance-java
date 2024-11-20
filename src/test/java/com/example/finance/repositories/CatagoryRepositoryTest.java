package com.example.finance.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.finance.entities.Catagory;
import com.example.finance.entities.User;

@DataJpaTest
class CatagoryRepositoryTest {

    @Autowired
    private CatagoryRepository catagoryRepository;

    private User testUser;
    private Catagory catagory1;
    private Catagory catagory2;

    @BeforeEach
    void setUp() {
        User issac = new User("Issac",    "Newton", "inewt23", "pass1234", "inewt12@gmail.com");

        catagory1 = new Catagory(issac, "Groceries");

        catagory2 = new Catagory(issac, "Rent");

        // Save test data to repository
        catagoryRepository.save(catagory1);
        catagoryRepository.save(catagory2);
    }

    @Test
    void testFindByUser() {
        // Act
        List<Catagory> categories = catagoryRepository.findByUser(testUser);

        // Assert
        assertEquals(2, categories.size(), "The size of the returned list should be 2");
        assertTrue(categories.contains(catagory1), "The list should contain catagory1");
        assertTrue(categories.contains(catagory2), "The list should contain catagory2");
    }

    @Test
    void testFindByName() {
        // Act
        Optional<Catagory> category = catagoryRepository.findByName("Groceries");

        // Assert
        assertTrue(category.isPresent(), "The category should be present");
        assertEquals(catagory1, category.get(), "The retrieved category should match catagory1");
    }

    @Test
    void testFindByName_NotFound() {
        // Act
        Optional<Catagory> category = catagoryRepository.findByName("Nonexistent");

        // Assert
        assertFalse(category.isPresent(), "The category should not be present for this name");
    }
}