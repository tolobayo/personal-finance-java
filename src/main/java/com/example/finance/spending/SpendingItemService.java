package com.example.finance.spending;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance.user.User;
import com.example.finance.user.UserRepository;

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
        return spendingItemRepository.getSpendingByUserAndMonth(user, date);
    }

    public void addSpendingItem(SpendingItem item) {
        
        spendingItemRepository.save(item);
    }

    public void deleteSpendingItem(Long id) {
        spendingItemRepository.deleteById(id);
    }
}
