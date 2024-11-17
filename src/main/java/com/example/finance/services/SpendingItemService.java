package com.example.finance.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance.repositories.SpendingItemRepository;
import com.example.finance.repositories.UserRepository;
import com.example.finance.entities.SpendingItem;
import com.example.finance.entities.User;

@Service
public class SpendingItemService {
    private final SpendingItemRepository spendingItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpendingItemService(UserRepository userRepository, SpendingItemRepository spendingItemRepository) {
        this.userRepository = userRepository;
        this.spendingItemRepository = spendingItemRepository;
    }

    public List<SpendingItem> getSpendingByMonth(Long userId,LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Get Spending by Month Failed: User not found"));
        return spendingItemRepository.getSpendingByUserAndYearAndMonth(user, date.getYear(),date.getMonthValue());
    }

    public void addSpendingItem(SpendingItem item) {
        
        spendingItemRepository.save(item);
    }

    public void deleteSpendingItem(Long id) {
        spendingItemRepository.deleteById(id);
    }
}
