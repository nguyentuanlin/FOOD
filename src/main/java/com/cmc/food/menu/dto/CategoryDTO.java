package com.cmc.food.menu.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    
    private String id;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    private List<String> foodItemIds = new ArrayList<>();
} 