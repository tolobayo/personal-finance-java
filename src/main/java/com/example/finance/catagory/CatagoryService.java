package com.example.finance.catagory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finance.user.User;
import com.example.finance.user.UserRepository;

@Service
public class CatagoryService {
    //TODO: Need a way to verify that only the user can access thier own catagories

    private final CatagoryRepository catagoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CatagoryService(CatagoryRepository catagoryRepository, UserRepository userRepository) {
        this.catagoryRepository = catagoryRepository;
        this.userRepository = userRepository;
    }

    public List<Catagory> getUserCatagories(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Find User Catagories Failed: User not found"));
        
        return catagoryRepository.getCatagoriesByUser(user);
    }

    public void addNewCatagory(Catagory newCatagory) {
        Optional<Catagory> existingCatagory = catagoryRepository.findCatagoryByName(newCatagory.getName());

        if (existingCatagory == null) catagoryRepository.save(newCatagory);
    }

    public void deleteCatagory(Long id) {
        boolean catagoryExists = catagoryRepository.existsById(id);

        if (!catagoryExists) throw new IllegalStateException("Spending catagory with the id " + id + " does not exist");
        else catagoryRepository.deleteById(id);
    }

    @Transactional
    public void updateCatagory(Long id, String name) {
        Catagory catagory = catagoryRepository.findById(id).orElseThrow(() -> new IllegalStateException("Spending catagory with the id " + id + " does not exist"));

        catagory.setName(name);
    }
}
