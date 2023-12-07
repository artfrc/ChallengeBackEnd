package com.dependenceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dependenceapi.domain.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    List<User> findAllByActiveTrue();

}
