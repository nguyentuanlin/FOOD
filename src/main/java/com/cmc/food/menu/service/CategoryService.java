package com.cmc.food.menu.service;

import com.cmc.food.menu.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    
    List<CategoryDTO> getAllCategories();
    
    CategoryDTO getCategoryById(Long id);
    
    CategoryDTO getCategoryByName(String name);
    
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    
    void deleteCategory(Long id);
    
    boolean existsByName(String name);
} 