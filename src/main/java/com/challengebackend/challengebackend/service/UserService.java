package com.challengebackend.challengebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.challengebackend.challengebackend.domain.User;
import com.challengebackend.challengebackend.dto.UpdateUserDTO;
import com.challengebackend.challengebackend.infra.exceptions.ValidationUpdateUserException;
import com.challengebackend.challengebackend.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Caching (
        evict = {
            @CacheEvict(value = "allUsers",allEntries = true),
            @CacheEvict(value = "usersActive",allEntries = true)
        }, put = {
            @CachePut(value = "user", key = "#data.id")
        }
    )
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

        System.out.println(">> UPDATE USER HERE!");

        return user;
    }

    @Caching(
        evict = {
            @CacheEvict(value = "allUsers",allEntries = true),
            @CacheEvict(value = "usersActive",allEntries = true)
        }
    )
    public void save(User user) {
        userRepository.save(user);
    }

    @Cacheable(value = "allUsers")
    public List<User> getAllUsers() {
        System.out.println(">> GET ALL USERS HERE!");
        return userRepository.findAll();
    }

    @Cacheable(value = "user", key = "#id")
    public User getUser(Long id) {
        System.out.println(">> GET USER HERE!");
        return userRepository.findById(id).get();
    }

    @Cacheable(value = "usersActive")
    public List<User> getActiveUsers() {
        System.out.println(">> GET ACTIVE USER HERE!");
        return userRepository.findAllByActiveTrue();
    }

    @Caching(
        evict = {
            @CacheEvict(value = "usersActive",allEntries = true),
            @CacheEvict(value = "allUsers",allEntries = true)
        },
        put = {
            @CachePut(value = "user", key = "#id")
        }
    )
    public User delete(Long id) {
        User user = this.getUser(id);
        user.setActive(false);
        System.out.println(">> DELETE USER HERE!");
        return user;
    }

}
