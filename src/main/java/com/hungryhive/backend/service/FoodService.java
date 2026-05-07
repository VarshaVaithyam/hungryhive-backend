package com.hungryhive.backend.service;

import com.hungryhive.backend.model.Food;
import com.hungryhive.backend.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    // ✅ ADD FOOD
    public Food addFood(Food food) {
        food.setStatus("AVAILABLE"); // VERY IMPORTANT
        return foodRepository.save(food);
    }

    // ✅ GET ALL FOOD
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    // ✅ GET FOOD BY ID
    public Food getFoodById(String id) {
        return foodRepository.findById(id).orElse(null);
    }

    // ✅ ACCEPT FOOD (Receiver requests it)
    public Food acceptFood(String id) {
        Optional<Food> optionalFood = foodRepository.findById(id);

        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();

            if ("AVAILABLE".equals(food.getStatus())) {
                food.setStatus("REQUESTED");
                return foodRepository.save(food);
            }
        }

        return null;
    }

    // ✅ COLLECT FOOD
    public Food collectFood(String id) {
        Optional<Food> optionalFood = foodRepository.findById(id);

        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();

            if ("REQUESTED".equals(food.getStatus())) {
                food.setStatus("COLLECTED");
                return foodRepository.save(food);
            }
        }

        return null;
    }

    // 🔥 NEW: MARK AS DELETED (DO NOT REMOVE FROM DB)
    public Food markAsDeleted(String id) {
        Optional<Food> optionalFood = foodRepository.findById(id);

        if (optionalFood.isPresent()) {
            Food food = optionalFood.get();
            food.setStatus("DELETED");
            return foodRepository.save(food);
        }

        return null;
    }

    // ❌ OLD DELETE (REMOVE THIS OR DO NOT USE)
    public Food markAsDeleted1(String id) {
    Food food = foodRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Food not found"));

    food.setStatus("DELETED"); // ✅ HERE
    return foodRepository.save(food);
}

    // ✅ GET AVAILABLE FOOD (MODIFIED FOR YOUR REQUIREMENT)
    public List<Food> getAvailableFood() {
        // Show AVAILABLE + DELETED in receiver
        return foodRepository.findByStatusIn(List.of("AVAILABLE", "DELETED"));
    }

    // ✅ GET FOOD BY LOCATION
    public List<Food> getFoodByLocation(String location) {
        return foodRepository.findByLocation(location);
    }
    public Food orderFood(String id) {
    Food food = foodRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Food not found"));

    food.setStatus("ORDERED"); // ✅ change status
    return foodRepository.save(food);
}
}