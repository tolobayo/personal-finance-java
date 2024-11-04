package com.example.finance.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.entities.SpendingItem;
import com.example.finance.services.SpendingItemService;

@RestController
@RequestMapping(path = "api/v1/spending")
public class SpendingItemController {
    private final SpendingItemService spendingItemService;

    @Autowired
    public SpendingItemController(SpendingItemService spendingItemService) {
        this.spendingItemService = spendingItemService;
    }

    @GetMapping
    public List<SpendingItem> getSpendingByMonth(Long userId, LocalDate date) {
        return spendingItemService.getSpendingByMonth(userId, date);
    }

    @PostMapping
    public void addSpendingItem(@RequestBody SpendingItem spendingItem) {
        spendingItemService.addSpendingItem(spendingItem);
    }

    @DeleteMapping(path="{spendingId}")
    public void deleteSpendingItem(@PathVariable("spendingId") Long id) {
        spendingItemService.deleteSpendingItem(id);
    }
}
