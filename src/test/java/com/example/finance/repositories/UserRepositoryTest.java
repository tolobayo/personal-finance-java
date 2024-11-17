package com.example.finance.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.finance.entities.User;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testGetUserByEmail() {

        User issac = new User("Issac",    "Newton", "inewt23", "pass1234", "inewt12@gmail.com");
        User polly = new User("Polly",    "Hashton", "polly56", "pass1234", "polly12@gmail.com");

        userRepository.saveAll(List.of(issac, polly));

        Optional<User> userFound = userRepository.findUserByEmail("inewt12@gmail.com");

        Assertions.assertNotNull(userFound);
        Assertions.assertEquals(issac.getId(), userFound.get().getId());
    }

}
