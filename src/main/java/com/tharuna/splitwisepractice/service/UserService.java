package com.tharuna.splitwisepractice.service;

import com.tharuna.splitwisepractice.exception.UserNotFoundException;
import com.tharuna.splitwisepractice.model.User;
import com.tharuna.splitwisepractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Add methods to interact with the UserRepository here
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id: " + id));
    }
}
