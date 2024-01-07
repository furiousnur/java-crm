package com.example.crm.services;

import com.example.crm.model.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserService {
    Map<String, Object> getUserList();
    Map<String, Object> save(User user);
    Map<String, Object> update(User user);
    Map<String, Object> userFind(Integer userId);
    Map<String, Object> deleteUser(Integer userId);
}
