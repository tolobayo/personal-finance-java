package com.example.finance.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.finance.entities.User;
import com.example.finance.repositories.UserRepository;

class UserServiceTest {

    @Mock
    private UserRepository userRepository; // Mocking the dependency

    @InjectMocks
    private UserService userService; // Service to be tested

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        
       
        // Set up test users
        user1 = new User(1, "Issac",    "Newton", "inewt23", "pass1234", "inewt12@gmail.com");
        user2 = new User(2, "Polly",    "Hashton", "polly56", "pass1234", "polly12@gmail.com");

        // Mock repository behavior
        Mockito.when(userRepository.save(Mockito.any(User.class)))
           .thenAnswer(invocation -> invocation.getArgument(0)); // Return the saved user
        
    }

    @Test
    void testGetUsers() {
        System.out.println(user1.toString());

        // Arrange
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // Act
        var users = userService.getUsers();

        // Assert
        assertEquals(2, users.size(), "The size of the returned user list should be 2");
        assertTrue(users.contains(user1), "The list should contain user1");
        assertTrue(users.contains(user2), "The list should contain user2");
        verify(userRepository, times(1)).findAll(); // Ensure findAll() was called once
    }

    @Test
    void testAddNewUser_Success() {
        // Arrange
        User newUser = new User(3, "Alice", "Wonderland", "alice56", "password1234","alice@gmail.com");
        when(userRepository.findUserByEmail(newUser.getEmail())).thenReturn(Optional.empty());

        // Act
        userService.addNewUser(newUser);

        // Assert
        verify(userRepository, times(1)).findUserByEmail("alice@gmail.com");
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testAddNewUser_ThrowsException() {
        // Arrange
        when(userRepository.findUserByEmail(user1.getEmail())).thenReturn(Optional.of(user1));

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            userService.addNewUser(user1);
        });
        assertEquals("User has already been registered", exception.getMessage());

        verify(userRepository, times(1)).findUserByEmail(user1.getEmail());
        verify(userRepository, never()).save(user1); // Ensure save() is never called
    }

    @Test
    void testDeleteUser_Success() {
    
        when(userRepository.existsById(user1.getId())).thenReturn(true);

        userService.deleteUser(user1.getId());

        verify(userRepository, times(1)).existsById(user1.getId());
        verify(userRepository, times(1)).deleteById(user1.getId());
    }

    @Test
    void testDeleteUser_ThrowsException() {
        
        when(userRepository.existsById(user1.getId())).thenReturn(false);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            userService.deleteUser(user1.getId());
        });
        assertEquals("User with the id 1 does not exist", exception.getMessage());

        verify(userRepository, times(1)).existsById(user1.getId());
        verify(userRepository, never()).deleteById(user1.getId()); // Ensure deleteById() is never called
    }

    @Test
    void testUpdateUser_Success() {
        
        when(userRepository.existsById(user1.getId())).thenReturn(true);
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        userService.updateUser(user1.getId(), "Jonathan", "Doe");

    
        assertEquals("Jonathan", user1.getFirstName());
        verify(userRepository, times(1)).existsById(user1.getId());
        verify(userRepository, times(1)).findById(user1.getId());
    }

    @Test
    void testUpdateUser_ThrowsException() {
        
        when(userRepository.existsById(user1.getId())).thenReturn(false);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            userService.updateUser(user1.getId(), "Jonathan", "Doe");
        });
        assertEquals("Update failed: user with the id 1 does not exist", exception.getMessage());

        verify(userRepository, times(1)).existsById(user1.getId());
        verify(userRepository, never()).findById(user1.getId()); // Ensure findById() is never called
    }
}