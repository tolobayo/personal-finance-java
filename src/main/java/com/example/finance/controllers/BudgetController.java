package com.example.finance.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.entities.Budget;
import com.example.finance.entities.Catagory;
import com.example.finance.services.BudgetService;

@RestController
@RequestMapping(path = "api/v1/budget") 
@CrossOrigin(origins = "http://localhost:5173", 
             allowedHeaders = "*", 
             methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.OPTIONS}, 
             allowCredentials = "true")
public class BudgetController {
    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // GET endpoint to retrieve monthly budgets for a user
    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Budget>> getMonthlyBudgets(@PathVariable("userId") Long userId) {
        List<Budget> budgets = budgetService.getAllMonthlyBudgets(userId);
        if (budgets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(budgets, HttpStatus.OK); // 200 OK
    }

    // POST endpoint to create a new monthly budget
    @PostMapping
    public ResponseEntity<String> createNewMonthlyBudget(@RequestBody Budget monthBudget, @RequestBody Long userId) {
        try {
            budgetService.createMonthlyBudget(monthBudget, userId);
            return new ResponseEntity<>("Monthly budget created successfully", HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    // PUT endpoint to update an existing monthly budget
    @PutMapping(path = "{budgetId}")
    public ResponseEntity<String> updateMonthlyBudget(
        @PathVariable("budgetId") Long id,
        @RequestBody Map<Catagory, Double> catagoryLimits,
        @RequestBody Double total) {
        
        try {
            budgetService.updateMonthlyBudget(id, catagoryLimits, total);
            return new ResponseEntity<>("Monthly budget updated successfully", HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // DELETE endpoint to delete a monthly budget
    @DeleteMapping(path = "{budgetId}")
    public ResponseEntity<String> deleteMonthlyBudget(@PathVariable("budgetId") Long id) {
        try {
            budgetService.deleteMonthlyBudget(id);
            return new ResponseEntity<>("Monthly budget deleted successfully", HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
