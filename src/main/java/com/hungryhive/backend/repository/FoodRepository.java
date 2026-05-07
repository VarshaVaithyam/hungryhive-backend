package com.hungryhive.backend.repository;

import com.hungryhive.backend.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {

    // Find food by status
    List<Food> findByStatusIn(List<String> statuses);

    // Find food by location
    List<Food> findByLocation(String location);

}