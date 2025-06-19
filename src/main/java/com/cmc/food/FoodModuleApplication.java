package com.cmc.food;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Food Module API",
                version = "1.0",
                description = "REST API for Food Module with User and Menu Management"
        )
)
public class FoodModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodModuleApplication.class, args);
    }
} 