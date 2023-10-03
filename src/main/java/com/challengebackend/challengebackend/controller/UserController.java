package com.challengebackend.challengebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengebackend.challengebackend.domain.User;
import com.challengebackend.challengebackend.dto.UserDTO;
import com.challengebackend.challengebackend.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @Transactional
    public User registerUser(@RequestBody @Valid UserDTO data) {
        User user = new User(data);
        userRepository.save(user);
        return user;
    }

}
