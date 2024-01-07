package com.example.crm.controller;

import com.example.crm.model.User;
import com.example.crm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> userList() {
        Map<String, Object> response = userService.getUserList();

        if (response.containsKey("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody User user){
        Map<String, Object> response = userService.save(user);
        if (response.containsKey("success")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // Use CREATED status for successful creation
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody User user){
        Map<String, Object> response = userService.update(user);
        if (response.containsKey("success")){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> userFind(@PathVariable Integer userId) {
        Map<String, Object> response = userService.userFind(userId);

        if (response.containsKey("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) {
        Map<String, Object> response = userService.deleteUser(userId);

        if (response.containsKey("success")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
