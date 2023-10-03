package com.challengebackend.challengebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challengebackend.challengebackend.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
