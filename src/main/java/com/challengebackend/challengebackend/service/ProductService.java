package com.challengebackend.challengebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challengebackend.challengebackend.domain.Product;
import com.challengebackend.challengebackend.dto.UpdateProductDTO;
import com.challengebackend.challengebackend.infra.exceptions.ValidationUpdateUserException;
import com.challengebackend.challengebackend.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product updateProduct(UpdateProductDTO data) {

        Product product = productRepository.findById(data.id()).get();
        
        var previousPrice = product.getPrice();
        
        try {

            if(data.price() != null) {
                if(data.price() < 0) {
                    throw new ValidationUpdateUserException("Price cannot be negative!");
                } else {
                    product.setPrice(data.price());
                }
            }

        } catch(Exception e) {
            product.setPrice(previousPrice);
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

    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

}
