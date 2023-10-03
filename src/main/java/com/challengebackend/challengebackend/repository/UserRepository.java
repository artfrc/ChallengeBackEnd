package com.challengebackend.challengebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challengebackend.challengebackend.domain.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
    
    List<User> findAllByActiveTrue();

}
