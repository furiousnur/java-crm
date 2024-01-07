package com.example.crm.services.impl;

import com.example.crm.model.User;
import com.example.crm.model.User;
import com.example.crm.repository.UserRepository;
import com.example.crm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceIml implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Object> getUserList() {
        List<User> users = userRepository.findAll();
        Map<String, Object> response = new HashMap<>();

        if (!users.isEmpty()){
            response.put("status", 200);
            response.put("success", "Users found");
            response.put("users", users);
        }else{
            response.put("status", 404);
            response.put("error", "Users not found");
        }
        return response;
    }

    @Override
    public Map<String, Object> save(User user) {
        User savedUser = userRepository.save(user);
        Map<String, Object> response = new HashMap<>();

        if (savedUser != null) {
            response.put("status", HttpStatus.CREATED.value()); // Set status to CREATED
            response.put("success", "User created successfully");
            response.put("user", savedUser); // Include the created user details
        } else {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", "User not created");
        }

        return response;
    }

    @Override
    public Map<String, Object> update(User user) {
        User obj = userRepository.findByUserId(user.getUserId());
        Map<String, Object> response = new HashMap<>();
        if (obj != null) {
            obj.setName(user.getName());
            obj.setEmail(user.getEmail());
            obj.setPassword(user.getPassword());
            obj.setPhone(user.getPhone());
            User updatedUser = userRepository.save(obj);
            response.put("status", HttpStatus.CREATED.value()); // Set status to CREATED
            response.put("success", "User updated successfully");
            response.put("user", updatedUser); // Include the created user details
        } else {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", "User not updated");
        }
        return response;
    }

    @Override
    public Map<String, Object> userFind(Integer userId) {
        User obj = userRepository.findByUserId(userId);
        Map<String, Object> response = new HashMap<>();

        if (obj != null) {
            response.put("status", 200);
            response.put("success", "User found");
            response.put("user", obj);
        } else {
            response.put("status", 404);
            response.put("error", "User not found");
        }

        return response;
    }

    @Override
    public Map<String, Object> deleteUser(Integer userId) {
        User obj = userRepository.findByUserId(userId);
        Map<String, Object> response = new HashMap<>();

        if (obj != null) {
            userRepository.deleteById(userId);
            response.put("status", 200);
            response.put("success", "User " + obj.getName() + " has been deleted successfully");
        } else {
            response.put("status", 404);
            response.put("error", "User not found");
        }

        return response;
    }
}
