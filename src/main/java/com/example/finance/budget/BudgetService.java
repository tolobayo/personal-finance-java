package com.example.finance.budget;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finance.catagory.Catagory;
import com.example.finance.user.User;
import com.example.finance.user.UserRepository;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository
 = userRepository
;
    }

    public List<Budget> getAllMonthlyBudgets(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Find Monthly Budgets Failed: User not found"));
        return budgetRepository.findBudgetByUser(user);
    }

    public void createMonthlyBudget(Budget newBudget, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Create Month Budget Failed: User not found"));

        Optional<Budget> existingBudget = budgetRepository.findBudgetByUserAndDate(user, newBudget.getDate());

        if (existingBudget != null) budgetRepository.save(newBudget);
    }

    public void deleteMonthlyBudget(Long id) {
        boolean budgetExists = budgetRepository.existsById(id);

        if (!budgetExists) throw new IllegalStateException("Monthly budget with the id " + id + " does not exist");
		else budgetRepository.deleteById(id);
    }

    @Transactional
    public void updateMonthlyBudget(Long id, Map<Catagory, Double> catagoryLimits, Double total) {
        //Check if budget exists
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new IllegalStateException("Monthly budget with the id " + id + " does not exist"));

        //Make sure budget total is greater than or equal to catagory totals
        double catagorySum = 0.0;

        for (double catagoryTotal : catagoryLimits.values()) {
            catagorySum += catagoryTotal;
        }

        if (total < catagorySum) throw new IllegalStateException("The total value for the monthly budget (" + total + ") cannot be less than the total set for catagory limits (" + catagorySum + ")");


        //Update budget
        budget.setCatagoryLimits(catagoryLimits);
        budget.setTotal(total);

    }

}
