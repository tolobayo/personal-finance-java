package com.example.finance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.entities.User;
import com.example.finance.services.UserService;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin(origins = "http://localhost:5173", 
             allowedHeaders = "*", 
             methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.OPTIONS}, 
             allowCredentials = "true")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET endpoint to retrieve all users
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(users, HttpStatus.OK); // 200 OK
    }

    // POST endpoint to register a new user
    @PostMapping
    public ResponseEntity<String> registerNewUser(@RequestBody User user) {
        try {
            userService.addNewUser(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED); // 201 Created
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    // DELETE endpoint to delete a user
    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT); // 204 No Content
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // PUT endpoint to update user information
    @PutMapping(path = "{userId}")
    public ResponseEntity<String> updateUser(
        @PathVariable("userId") Long id, 
        @RequestParam(required = false) String firstName, 
        @RequestParam(required = false) String lastName) {
        
        try {
            userService.updateUser(id, firstName, lastName);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK); // 200 OK
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
