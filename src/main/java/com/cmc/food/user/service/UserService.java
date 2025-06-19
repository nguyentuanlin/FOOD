package com.cmc.food.user.service;

import com.cmc.food.user.dto.CreateUserRequest;
import com.cmc.food.user.dto.UserDTO;

import java.util.List;

public interface UserService {
    
    List<UserDTO> getAllUsers();
    
    UserDTO getUserById(String id);
    
    UserDTO getUserByUsername(String username);
    
    UserDTO createUser(CreateUserRequest createUserRequest);
    
    UserDTO updateUser(String id, UserDTO userDTO);
    
    void deleteUser(String id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
} 