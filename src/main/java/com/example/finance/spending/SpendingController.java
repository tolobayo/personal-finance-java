package com.example.finance.spending;

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

@RestController
@RequestMapping(path = "api/v1/spending")
public class SpendingController {
    private final SpendingService spendingService;

    @Autowired
    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    @GetMapping
    public List<Spending> getSpendingByMonth(Long userId, LocalDate date) {
        return spendingService.getSpendingByMonth(userId, date);
    }

    @PostMapping
    public void addSpendingItem(@RequestBody Spending spendingItem) {
        spendingService.addSpendingItem(spendingItem);
    }

    @DeleteMapping(path="{spendingId}")
    public void deleteSpendingItem(@PathVariable("spendingId") Long id) {
        spendingService.deleteSpendingItem(id);
    }
}
