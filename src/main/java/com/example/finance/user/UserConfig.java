package com.example.finance.user;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User joe = new User("Joe", "Robinson", "jrob23", "password123", "jrob23@gmail.com");
            User molly = new User("Molly", "Robinson", "mrob23", "password123", "mrob23@gmail.com");

            repository.saveAll(List.of(joe, molly));

        };
    }
}
