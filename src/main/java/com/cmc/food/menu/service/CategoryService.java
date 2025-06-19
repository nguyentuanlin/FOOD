package com.cmc.food.menu.service;

import com.cmc.food.menu.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    
    List<CategoryDTO> getAllCategories();
    
    CategoryDTO getCategoryById(String id);
    
    CategoryDTO getCategoryByName(String name);
    
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    
    CategoryDTO updateCategory(String id, CategoryDTO categoryDTO);
    
    void deleteCategory(String id);
    
    boolean existsByName(String name);
} 