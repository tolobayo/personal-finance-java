package com.example.finance.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.entities.Budget;
import com.example.finance.entities.Catagory;
import com.example.finance.services.BudgetService;

@RestController
@RequestMapping(path = "api/vi/budget")
public class BudgetController {
    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping(path="{userId}")
    public List<Budget> getMonthlyBudgets(@PathVariable("userId") Long userId) {
        return budgetService.getAllMonthlyBudgets(userId);
    }

    @PostMapping
    public void createNewMonthlyBudget(@RequestBody Budget monthBudget, @RequestBody Long userId) {
        budgetService.createMonthlyBudget(monthBudget, userId);
    }

    @PutMapping(path="{budgetId}")
    public void updateMonthlyBudget(@PathVariable("budgetId") Long id, Map<Catagory, Double> catagoryLimits, Double total) {
        budgetService.updateMonthlyBudget(id, catagoryLimits, total);
    }

    @DeleteMapping(path="{budgetId}")
    public void deleteMonthlyBudget(@PathVariable("budgetId") Long id) {
        budgetService.deleteMonthlyBudget(id);
    }

}
