package com.challengebackend.challengebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challengebackend.challengebackend.domain.User;
import com.challengebackend.challengebackend.dto.UpdateUserDTO;
import com.challengebackend.challengebackend.infra.ValidationUpdateUserException;
import com.challengebackend.challengebackend.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User updateUser(UpdateUserDTO data) {

        User user = userRepository.findById(data.id()).get();
        
        var previousBalance = user.getBalance();
        var previousActive = user.getActive();
        
        try {

            if(data.balance() != null) {
                var newBalance = previousBalance + data.balance();
                if(newBalance < 0) {
                    throw new ValidationUpdateUserException("Balance cannot be negative!");
                } else {
                    user.setBalance(newBalance);
                }
            }

            if(data.active() != null) {
                user.setActive(data.active());
            }

        } catch(Exception e) {
            user.setBalance(previousBalance);
            user.setActive(previousActive);
            throw new ValidationUpdateUserException(e.getMessage());
        }

        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getActiveUsers() {
        return userRepository.findAllByActiveTrue();
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        user.setActive(false);
    }

}
