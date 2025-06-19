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
    public List<FoodItemDTO> getFoodItemsByCategory(String categoryId) {
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
    public FoodItemDTO getFoodItemById(String id) {
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
        foodItem.setCategoryId(category.getId());
        foodItem.setCategoryName(category.getName());
        foodItem.setImageUrl(foodItemDTO.getImageUrl());
        foodItem.setAvailable(foodItemDTO.isAvailable());
        foodItem.prePersist();
        
        FoodItem savedFoodItem = foodItemRepository.save(foodItem);
        
        // Update the category with the new food item ID
        if (!category.getFoodItemIds().contains(savedFoodItem.getId())) {
            category.getFoodItemIds().add(savedFoodItem.getId());
            categoryRepository.save(category);
        }
        
        return convertToDTO(savedFoodItem);
    }
    
    @Override
    public FoodItemDTO updateFoodItem(String id, FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with id: " + id));
        
        Category category = categoryRepository.findById(foodItemDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + foodItemDTO.getCategoryId()));
        
        // If category is changed, update the references
        if (!foodItem.getCategoryId().equals(category.getId())) {
            // Remove from old category
            Category oldCategory = categoryRepository.findById(foodItem.getCategoryId())
                    .orElse(null);
            if (oldCategory != null) {
                oldCategory.getFoodItemIds().remove(foodItem.getId());
                categoryRepository.save(oldCategory);
            }
            
            // Add to new category
            if (!category.getFoodItemIds().contains(foodItem.getId())) {
                category.getFoodItemIds().add(foodItem.getId());
                categoryRepository.save(category);
            }
        }
        
        foodItem.setName(foodItemDTO.getName());
        foodItem.setDescription(foodItemDTO.getDescription());
        foodItem.setPrice(foodItemDTO.getPrice());
        foodItem.setCategoryId(category.getId());
        foodItem.setCategoryName(category.getName());
        foodItem.setImageUrl(foodItemDTO.getImageUrl());
        foodItem.setAvailable(foodItemDTO.isAvailable());
        foodItem.preUpdate();
        
        FoodItem updatedFoodItem = foodItemRepository.save(foodItem);
        return convertToDTO(updatedFoodItem);
    }
    
    @Override
    public void deleteFoodItem(String id) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with id: " + id));
        
        // Remove the food item ID from the category
        Category category = categoryRepository.findById(foodItem.getCategoryId())
                .orElse(null);
        if (category != null) {
            category.getFoodItemIds().remove(id);
            categoryRepository.save(category);
        }
        
        foodItemRepository.deleteById(id);
    }
    
    @Override
    public void updateFoodItemAvailability(String id, boolean available) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food item not found with id: " + id));
        
        foodItem.setAvailable(available);
        foodItem.preUpdate();
        foodItemRepository.save(foodItem);
    }
    
    private FoodItemDTO convertToDTO(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setName(foodItem.getName());
        foodItemDTO.setDescription(foodItem.getDescription());
        foodItemDTO.setPrice(foodItem.getPrice());
        foodItemDTO.setCategoryId(foodItem.getCategoryId());
        foodItemDTO.setCategoryName(foodItem.getCategoryName());
        foodItemDTO.setImageUrl(foodItem.getImageUrl());
        foodItemDTO.setAvailable(foodItem.isAvailable());
        return foodItemDTO;
    }
} 