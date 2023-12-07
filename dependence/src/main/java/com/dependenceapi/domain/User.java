package com.dependenceapi.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
    
@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private String document;
    private String email;
    private Double balance;
    private Boolean active;

    @ManyToMany(mappedBy = "usersWhoPurchased")
    private Set<Product> purchasedProducts;
    
    
    public User(String name, String document, String email) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.balance = 0.;
        this.active = true;
    }
    
    public Set<Product> getPurchasedProducts() {
        return purchasedProducts;
    }
    
    public void setPurchasedProducts(Set<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public void addNewProduct(Product product) {
        this.purchasedProducts.add(product);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
