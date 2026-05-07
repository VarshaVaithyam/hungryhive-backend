package com.hungryhive.backend.controller;

import com.hungryhive.backend.model.Food;
import com.hungryhive.backend.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "*")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/test")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("Food API is working");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFood(@RequestBody Food food) {
        try {
            System.out.println("==== FOOD RECEIVED ====");
            System.out.println("Food Name: " + food.getFoodName());
            System.out.println("Organization: " + food.getOrganization());
            System.out.println("Donor Name: " + food.getDonorName());
            System.out.println("Phone: " + food.getPhoneNumber());
            System.out.println("Location: " + food.getLocation());
            System.out.println("Quantity: " + food.getQuantity());
            System.out.println("Description: " + food.getDescription());

            Food savedFood = foodService.addFood(food);

            return ResponseEntity.status(201).body(savedFood);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error while saving food");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Food>> getAllFood() {
        return ResponseEntity.ok(foodService.getAllFood());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Food>> getAvailableFood() {
        return ResponseEntity.ok(foodService.getAvailableFood());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(foodService.getFoodById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Food not found");
        }
    }

    @GetMapping("/location/{city}")
    public ResponseEntity<List<Food>> getFoodByLocation(@PathVariable String city) {
        return ResponseEntity.ok(foodService.getFoodByLocation(city));
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptFood(@PathVariable String id) {
        try {
            return ResponseEntity.ok(foodService.acceptFood(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Food not found");
        }
    }

    @PutMapping("/collect/{id}")
    public ResponseEntity<?> collectFood(@PathVariable String id) {
        try {
            return ResponseEntity.ok(foodService.collectFood(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Food not found");
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> orderFood(@PathVariable String id) {
        try {
            return ResponseEntity.ok(foodService.orderFood(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Food not found");
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable String id) {
        return ResponseEntity.ok(foodService.markAsDeleted(id));
    }
}