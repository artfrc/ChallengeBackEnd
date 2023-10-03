package com.challengebackend.challengebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengebackend.challengebackend.domain.User;
import com.challengebackend.challengebackend.dto.UpdateUserDTO;
import com.challengebackend.challengebackend.dto.UserDTO;
import com.challengebackend.challengebackend.infra.ValidationException;
import com.challengebackend.challengebackend.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> users = userService.getActiveUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO data) {
        User user = new User(data);
        try {
            userService.save(user);
        } catch(Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return ResponseEntity.ok("User created successfully!");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<User> updateUser(@RequestBody @Valid UpdateUserDTO data) {
        User user = userService.updateUser(data);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Exception> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<Exception>(HttpStatus.OK);
    }

}
