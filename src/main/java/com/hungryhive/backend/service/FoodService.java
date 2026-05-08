package com.hungryhive.backend.service;

import com.hungryhive.backend.model.Food;
import com.hungryhive.backend.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food addFood(Food food) {
        food.setStatus("AVAILABLE");
        return foodRepository.save(food);
    }

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public Food getFoodById(String id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }

    public Food acceptFood(String id) {
        Food food = getFoodById(id);

        if ("AVAILABLE".equals(food.getStatus())) {
            food.setStatus("REQUESTED");
            return foodRepository.save(food);
        }

        return food;
    }

    public Food collectFood(String id) {
        Food food = getFoodById(id);

        if ("REQUESTED".equals(food.getStatus())) {
            food.setStatus("COLLECTED");
            return foodRepository.save(food);
        }

        return food;
    }

    public Food orderFood(String id) {
        Food food = getFoodById(id);

        if ("AVAILABLE".equals(food.getStatus())
                || "REQUESTED".equals(food.getStatus())) {
            food.setStatus("ORDERED");
            return foodRepository.save(food);
        }

        return food;
    }

    public Food markAsDeleted(String id, String userId) {
        Food food = getFoodById(id);

        if (food.getOwnerUserId() == null || userId == null) {
            throw new RuntimeException("Owner verification failed");
        }

        if (!food.getOwnerUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this food");
        }

        food.setStatus("DELETED");
        return foodRepository.save(food);
    }

    public List<Food> getAvailableFood() {
        return foodRepository.findByStatusIn(
                List.of("AVAILABLE", "REQUESTED", "ORDERED")
        );
    }

    public List<Food> getFoodByLocation(String location) {
        return foodRepository.findByLocation(location);
    }
}