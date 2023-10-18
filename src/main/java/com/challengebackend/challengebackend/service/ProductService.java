package com.challengebackend.challengebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.challengebackend.challengebackend.domain.Product;
import com.challengebackend.challengebackend.dto.UpdateProductDTO;
import com.challengebackend.challengebackend.infra.exceptions.ValidationUpdateUserException;
import com.challengebackend.challengebackend.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @CachePut(value = "product", key = "#data.id")
    public Product updateProduct(UpdateProductDTO data) {

        Product product = productRepository.findById(data.id()).get();
        
        var previousPrice = product.getPrice();
        var previousPurchasedProducts = product.getUsersWhoPurchased();
        
        try {

            if(data.price() != null) {
                if(data.price() < 0) {
                    throw new ValidationUpdateUserException("Price cannot be negative!");
                } else {
                    product.setPrice(data.price());
                }
            }

            if(data.user() != null) {
                product.addNewUser(data.user());
            }

        } catch(Exception e) {
            product.setPrice(previousPrice);
            product.setUsersWhoPurchased(previousPurchasedProducts);
            throw new ValidationUpdateUserException(e.getMessage());
        }

        return product;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Cacheable(value = "product", key = "#id")
    public Product getProduct(Long id) {
        System.out.println(">> GET PRODUCT HERE!");

        return productRepository.findById(id).get();
    }

    @CacheEvict(value = "product", key = "#id")
    public void delete(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        System.out.println(">> DELETE PRODUCT HERE!");
    }
    
}
