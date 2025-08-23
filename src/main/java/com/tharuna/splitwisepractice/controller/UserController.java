package com.tharuna.splitwisepractice.controller;

import com.tharuna.splitwisepractice.model.User;
import com.tharuna.splitwisepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // Add methods to handle HTTP requests here, e.g., addUser, getUserById, etc.
    // Example:
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }
}
