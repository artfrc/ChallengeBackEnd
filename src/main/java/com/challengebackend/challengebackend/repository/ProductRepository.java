package com.challengebackend.challengebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challengebackend.challengebackend.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
