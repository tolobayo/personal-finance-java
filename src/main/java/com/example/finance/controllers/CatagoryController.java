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
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.entities.Catagory;
import com.example.finance.services.CatagoryService;

@RestController
@RequestMapping(path = "api/v1/catagory")
@CrossOrigin(origins = "http://localhost:5173", 
             allowedHeaders = "*", 
             methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.OPTIONS}, 
             allowCredentials = "true")
public class CatagoryController {

    private final CatagoryService catagoryService;

    @Autowired
    public CatagoryController(CatagoryService catagoryService) {
        this.catagoryService = catagoryService;
    }

    // GET endpoint to retrieve categories for a user
    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Catagory>> getUserCatagories(@PathVariable("userId") Long id) {
        List<Catagory> catagories = catagoryService.getUserCatagories(id);
        if (catagories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(catagories, HttpStatus.OK); // 200 OK
    }

    // POST endpoint to add a new category
    @PostMapping
    public ResponseEntity<String> addNewCatagory(@RequestBody Catagory newCatagory) {
        try {
            catagoryService.addNewCatagory(newCatagory);
            return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    // DELETE endpoint to delete a category
    @DeleteMapping(path = "{catagoryId}")
    public ResponseEntity<String> deleteCatagory(@PathVariable("catagoryId") Long id) {
        try {
            catagoryService.deleteCatagory(id);
            return new ResponseEntity<>("Category deleted successfully", HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // PUT endpoint to update a category
    @PutMapping(path = "{catagoryId}")
    public ResponseEntity<String> updateCatagory(@PathVariable("catagoryId") Long id, @RequestBody String label) {
        try {
            catagoryService.updateCatagory(id, label);
            return new ResponseEntity<>("Category updated successfully", HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
