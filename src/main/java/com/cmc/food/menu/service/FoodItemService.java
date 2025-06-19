package com.cmc.food.menu.service;

import com.cmc.food.menu.dto.FoodItemDTO;

import java.util.List;

public interface FoodItemService {
    
    List<FoodItemDTO> getAllFoodItems();
    
    List<FoodItemDTO> getAllAvailableFoodItems();
    
    List<FoodItemDTO> getFoodItemsByCategory(String categoryId);
    
    List<FoodItemDTO> searchFoodItems(String keyword);
    
    FoodItemDTO getFoodItemById(String id);
    
    FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO);
    
    FoodItemDTO updateFoodItem(String id, FoodItemDTO foodItemDTO);
    
    void deleteFoodItem(String id);
    
    void updateFoodItemAvailability(String id, boolean available);
} 