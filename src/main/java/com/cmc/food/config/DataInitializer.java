package com.cmc.food.config;

import com.cmc.food.menu.model.Category;
import com.cmc.food.menu.model.FoodItem;
import com.cmc.food.menu.repository.CategoryRepository;
import com.cmc.food.menu.repository.FoodItemRepository;
import com.cmc.food.user.model.User;
import com.cmc.food.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FoodItemRepository foodItemRepository;
    
    @Override
    public void run(String... args) {
        // Add sample users
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword("admin123");
            admin.setFullName("Admin User");
            
            User user1 = new User();
            user1.setUsername("user1");
            user1.setEmail("user1@example.com");
            user1.setPassword("user123");
            user1.setFullName("Regular User");
            
            userRepository.saveAll(List.of(admin, user1));
        }
        
        // Add food categories
        if (categoryRepository.count() == 0) {
            Category mainDish = new Category();
            mainDish.setName("Main Dishes");
            mainDish.setDescription("Main course dishes");
            
            Category appetizer = new Category();
            appetizer.setName("Appetizers");
            appetizer.setDescription("Starters and appetizers");
            
            Category dessert = new Category();
            dessert.setName("Desserts");
            dessert.setDescription("Sweet dishes served at the end of a meal");
            
            Category drinks = new Category();
            drinks.setName("Drinks");
            drinks.setDescription("Beverages and drinks");
            
            categoryRepository.saveAll(List.of(mainDish, appetizer, dessert, drinks));
            
            // Add food items
            FoodItem item1 = new FoodItem();
            item1.setName("Spaghetti Carbonara");
            item1.setDescription("Classic Italian pasta dish with eggs, cheese, and pancetta");
            item1.setPrice(new BigDecimal("12.99"));
            item1.setCategory(mainDish);
            item1.setImageUrl("https://example.com/images/spaghetti.jpg");
            item1.setAvailable(true);
            
            FoodItem item2 = new FoodItem();
            item2.setName("Caesar Salad");
            item2.setDescription("Fresh romaine lettuce with Caesar dressing, croutons, and parmesan");
            item2.setPrice(new BigDecimal("8.99"));
            item2.setCategory(appetizer);
            item2.setImageUrl("https://example.com/images/caesar-salad.jpg");
            item2.setAvailable(true);
            
            FoodItem item3 = new FoodItem();
            item3.setName("Chocolate Cake");
            item3.setDescription("Rich chocolate cake with chocolate ganache");
            item3.setPrice(new BigDecimal("6.99"));
            item3.setCategory(dessert);
            item3.setImageUrl("https://example.com/images/chocolate-cake.jpg");
            item3.setAvailable(true);
            
            FoodItem item4 = new FoodItem();
            item4.setName("Iced Coffee");
            item4.setDescription("Cold brewed coffee served with ice");
            item4.setPrice(new BigDecimal("3.99"));
            item4.setCategory(drinks);
            item4.setImageUrl("https://example.com/images/iced-coffee.jpg");
            item4.setAvailable(true);
            
            foodItemRepository.saveAll(List.of(item1, item2, item3, item4));
        }
    }
} 