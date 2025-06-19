package com.cmc.food.menu.service;

import com.cmc.food.menu.dto.FoodItemDTO;

import java.util.List;

public interface FoodItemService {
    
    List<FoodItemDTO> getAllFoodItems();
    
    List<FoodItemDTO> getAllAvailableFoodItems();
    
    List<FoodItemDTO> getFoodItemsByCategory(Long categoryId);
    
    List<FoodItemDTO> searchFoodItems(String keyword);
    
    FoodItemDTO getFoodItemById(Long id);
    
    FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO);
    
    FoodItemDTO updateFoodItem(Long id, FoodItemDTO foodItemDTO);
    
    void deleteFoodItem(Long id);
    
    void updateFoodItemAvailability(Long id, boolean available);
} 