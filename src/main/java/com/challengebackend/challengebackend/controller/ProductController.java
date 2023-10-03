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

import com.challengebackend.challengebackend.domain.Product;
import com.challengebackend.challengebackend.dto.ProductDTO;
import com.challengebackend.challengebackend.dto.UpdateProductDTO;
import com.challengebackend.challengebackend.infra.ValidationException;
import com.challengebackend.challengebackend.service.ProductService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerProduct(@RequestBody @Valid ProductDTO data) {
        Product product = new Product(data);
        try {
            productService.save(product);
        } catch(Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return ResponseEntity.ok("Product created successfully!");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid UpdateProductDTO data) {
        Product product = productService.updateProduct(data);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Exception> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<Exception>(HttpStatus.OK);
    }

}
