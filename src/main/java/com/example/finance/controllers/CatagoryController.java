package com.example.finance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.entities.Catagory;
import com.example.finance.services.CatagoryService;

@RestController
@RequestMapping(path = "api/vi/catagory")
public class CatagoryController {

    private final CatagoryService catagoryService;

    @Autowired
    public CatagoryController(CatagoryService catagoryService) {
        this.catagoryService = catagoryService;
    }

    @GetMapping(path="{userId}")
    public List<Catagory> getUserCatagories(@PathVariable("userId") Long id) {
        return catagoryService.getUserCatagories(id);
    }

    @PostMapping
    public void addNewCatagory(Catagory newCatagory) {
        catagoryService.addNewCatagory(newCatagory);
    }

    @DeleteMapping(path = "{catagoryId}")
    public void deleteCatagory(@PathVariable("catagoryId") Long id) {
        catagoryService.deleteCatagory(id);
    }

    @PutMapping(path = "{catagoryId}")
    public void updateCatagory(@PathVariable("catagoryId") Long id, String label) {
        catagoryService.updateCatagory(id, label);

    }


}
