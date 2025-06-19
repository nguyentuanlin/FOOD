package com.cmc.food.menu.repository;

import com.cmc.food.menu.model.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    
    List<FoodItem> findByCategoryId(String categoryId);
    
    List<FoodItem> findByNameContainingIgnoreCase(String name);
    
    List<FoodItem> findByAvailableTrue();
} 