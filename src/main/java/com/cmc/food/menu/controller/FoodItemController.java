package com.cmc.food.menu.controller;

import com.cmc.food.menu.dto.FoodItemDTO;
import com.cmc.food.menu.service.FoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
@RequiredArgsConstructor
@Tag(name = "Food Menu Management", description = "API endpoints for food menu item management")
public class FoodItemController {
    
    private final FoodItemService foodItemService;
    
    @GetMapping
    @Operation(summary = "Get all food items", description = "Retrieves a list of all food items")
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }
    
    @GetMapping("/available")
    @Operation(summary = "Get available food items", description = "Retrieves a list of all available food items")
    public ResponseEntity<List<FoodItemDTO>> getAllAvailableFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllAvailableFoodItems());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get food item by ID", description = "Retrieves a food item by its ID")
    public ResponseEntity<FoodItemDTO> getFoodItemById(@PathVariable String id) {
        return ResponseEntity.ok(foodItemService.getFoodItemById(id));
    }
    
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get food items by category", description = "Retrieves food items by category ID")
    public ResponseEntity<List<FoodItemDTO>> getFoodItemsByCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(foodItemService.getFoodItemsByCategory(categoryId));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search food items", description = "Searches food items by name")
    public ResponseEntity<List<FoodItemDTO>> searchFoodItems(@RequestParam String keyword) {
        return ResponseEntity.ok(foodItemService.searchFoodItems(keyword));
    }
    
    @PostMapping
    @Operation(summary = "Create food item", description = "Creates a new food item")
    public ResponseEntity<FoodItemDTO> createFoodItem(@Valid @RequestBody FoodItemDTO foodItemDTO) {
        return new ResponseEntity<>(foodItemService.createFoodItem(foodItemDTO), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update food item", description = "Updates an existing food item")
    public ResponseEntity<FoodItemDTO> updateFoodItem(@PathVariable String id, @Valid @RequestBody FoodItemDTO foodItemDTO) {
        return ResponseEntity.ok(foodItemService.updateFoodItem(id, foodItemDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete food item", description = "Deletes a food item by its ID")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/availability")
    @Operation(summary = "Update food item availability", description = "Updates the availability of a food item")
    public ResponseEntity<Void> updateFoodItemAvailability(@PathVariable String id, @RequestParam boolean available) {
        foodItemService.updateFoodItemAvailability(id, available);
        return ResponseEntity.noContent().build();
    }
} 