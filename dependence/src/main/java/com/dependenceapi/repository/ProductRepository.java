package com.dependenceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dependenceapi.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
