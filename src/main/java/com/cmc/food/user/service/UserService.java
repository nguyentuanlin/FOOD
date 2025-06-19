package com.cmc.food.user.service;

import com.cmc.food.user.dto.CreateUserRequest;
import com.cmc.food.user.dto.UserDTO;

import java.util.List;

public interface UserService {
    
    List<UserDTO> getAllUsers();
    
    UserDTO getUserById(Long id);
    
    UserDTO getUserByUsername(String username);
    
    UserDTO createUser(CreateUserRequest createUserRequest);
    
    UserDTO updateUser(Long id, UserDTO userDTO);
    
    void deleteUser(Long id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
} 