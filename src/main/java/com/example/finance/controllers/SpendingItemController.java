package com.example.finance.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.entities.SpendingItem;
import com.example.finance.services.SpendingItemService;

@RestController
@RequestMapping(path = "api/v1/spending")
@CrossOrigin(origins = "http://localhost:5173", 
             allowedHeaders = "*", 
             methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.OPTIONS}, 
             allowCredentials = "true")
public class SpendingItemController {

    private final SpendingItemService spendingItemService;

    @Autowired
    public SpendingItemController(SpendingItemService spendingItemService) {
        this.spendingItemService = spendingItemService;
    }

    // GET endpoint to retrieve spending items by user and month
    @GetMapping
    public ResponseEntity<List<SpendingItem>> getSpendingByMonth(Long userId, LocalDate date) {
        List<SpendingItem> spendingItems = spendingItemService.getSpendingByMonth(userId, date);
        if (spendingItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(spendingItems, HttpStatus.OK); // 200 OK
    }

    // POST endpoint to add a new spending item
    @PostMapping
    public ResponseEntity<String> addSpendingItem(@RequestBody SpendingItem spendingItem) {
        try {
            spendingItemService.addSpendingItem(spendingItem);
            return new ResponseEntity<>("Spending item added successfully", HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    // DELETE endpoint to delete a spending item
    @DeleteMapping(path = "{spendingId}")
    public ResponseEntity<String> deleteSpendingItem(@PathVariable("spendingId") Long id) {
        try {
            spendingItemService.deleteSpendingItem(id);
            return new ResponseEntity<>("Spending item deleted successfully", HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
