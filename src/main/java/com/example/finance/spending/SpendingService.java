package com.example.finance.spending;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance.user.User;
import com.example.finance.user.UserRepository;

@Service
public class SpendingService {
    private final SpendingRepository spendingRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpendingService(UserRepository userRepository, SpendingRepository spendingRepository) {
        this.userRepository = userRepository;
        this.spendingRepository = spendingRepository;
    }

    public List<Spending> getSpendingByMonth(Long userId,LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Get Spending by Month Failed: User not found"));
        return spendingRepository.getSpendingByUserAndMonth(user, date);
    }

    public void addSpendingItem(Spending item) {
        
        spendingRepository.save(item);
    }

    public void deleteSpendingItem(Long id) {
        spendingRepository.deleteById(id);
    }
}
