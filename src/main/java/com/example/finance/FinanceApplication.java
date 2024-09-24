package com.example.finance;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.user.User;

@SpringBootApplication
@RestController
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}
	@GetMapping
	public List<User> getUsers() {
		User jenny = new User(1, "jenny", "jenny1", "jenny1", "jenny1@gmail.com");
		User mark = new User(2, "mark", "mark1", "mark1", "mark1@gmail.com");
		return List.of(jenny, mark);
	}

}
