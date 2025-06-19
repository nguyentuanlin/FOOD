package com.cmc.food.menu.service;

import com.cmc.food.common.exception.ResourceNotFoundException;
import com.cmc.food.menu.dto.FoodItemDTO;
import com.cmc.food.menu.model.Category;
import com.cmc.food.menu.model.FoodItem;
import com.cmc.food.menu.repository.CategoryRepository;
import com.cmc.food.menu.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {
    
    private final FoodItemRepository foodItemRepository;
    private final CategoryRepository categoryRepository;
    
    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        return foodItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FoodItemDTO> getAllAvailableFoodItems() {
        return foodItemRepository.findByAvailableTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FoodItemDTO> getFoodItemsByCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found with id: " + categoryId);
        }
        
        return foodItemRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FoodItemDTO> searchFoodItems(String keyword) {
        return foodItemRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public FoodItemDTO getFoodItemById(Long id) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with id: " + id));
        return convertToDTO(foodItem);
    }
    
    @Override
    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        Category category = categoryRepository.findById(foodItemDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + foodItemDTO.getCategoryId()));
        
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemDTO.getName());
        foodItem.setDescription(foodItemDTO.getDescription());
        foodItem.setPrice(foodItemDTO.getPrice());
        foodItem.setCategory(category);
        foodItem.setImageUrl(foodItemDTO.getImageUrl());
        foodItem.setAvailable(foodItemDTO.isAvailable());
        
        FoodItem savedFoodItem = foodItemRepository.save(foodItem);
        return convertToDTO(savedFoodItem);
    }
    
    @Override
    public FoodItemDTO updateFoodItem(Long id, FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with id: " + id));
        
        Category category = categoryRepository.findById(foodItemDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + foodItemDTO.getCategoryId()));
        
        foodItem.setName(foodItemDTO.getName());
        foodItem.setDescription(foodItemDTO.getDescription());
        foodItem.setPrice(foodItemDTO.getPrice());
        foodItem.setCategory(category);
        foodItem.setImageUrl(foodItemDTO.getImageUrl());
        foodItem.setAvailable(foodItemDTO.isAvailable());
        
        FoodItem updatedFoodItem = foodItemRepository.save(foodItem);
        return convertToDTO(updatedFoodItem);
    }
    
    @Override
    public void deleteFoodItem(Long id) {
        if (!foodItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Food item not found with id: " + id);
        }
        foodItemRepository.deleteById(id);
    }
    
    @Override
    public void updateFoodItemAvailability(Long id, boolean available) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with id: " + id));
        
        foodItem.setAvailable(available);
        foodItemRepository.save(foodItem);
    }
    
    private FoodItemDTO convertToDTO(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setName(foodItem.getName());
        foodItemDTO.setDescription(foodItem.getDescription());
        foodItemDTO.setPrice(foodItem.getPrice());
        foodItemDTO.setCategoryId(foodItem.getCategory().getId());
        foodItemDTO.setCategoryName(foodItem.getCategory().getName());
        foodItemDTO.setImageUrl(foodItem.getImageUrl());
        foodItemDTO.setAvailable(foodItem.isAvailable());
        return foodItemDTO;
    }
} 