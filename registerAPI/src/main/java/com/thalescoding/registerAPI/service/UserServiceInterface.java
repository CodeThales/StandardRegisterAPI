package com.thalescoding.registerAPI.service;

import com.thalescoding.registerAPI.model.Role;
import com.thalescoding.registerAPI.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAllUsers();

    User getUser(String userLogin);

    //Optional<User> getUserById(Long id);
    ResponseEntity<Object> getUserById(Long id);

    User saveUser(User user);

    ResponseEntity<Object> updateUser(User user);

    ResponseEntity<String> deleteUser(Long id);

    Role saveRole(Role role);
    void addRoleToUser(String userLogin, String roleName);



}
