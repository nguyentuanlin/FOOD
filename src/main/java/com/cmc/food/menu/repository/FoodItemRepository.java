package com.cmc.food.menu.repository;

import com.cmc.food.menu.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    
    List<FoodItem> findByCategoryId(Long categoryId);
    
    List<FoodItem> findByNameContainingIgnoreCase(String name);
    
    List<FoodItem> findByAvailableTrue();
} 