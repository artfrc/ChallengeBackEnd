package com.dependenceapi.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String name;
    private Double price;

    @JsonIgnore
    @ManyToMany
    @JoinTable( name = "product_user",
                joinColumns = @JoinColumn(name = "product_fk"),
                inverseJoinColumns = @JoinColumn(name = "user_fk"))
    private Set<User> usersWhoPurchased;
    
    
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    
    public Set<User> getUsersWhoPurchased() {
        return usersWhoPurchased;
    }
    
    public void setUsersWhoPurchased(Set<User> usersWhoPurchased) {
        this.usersWhoPurchased = usersWhoPurchased;
    }

    public void addNewUser(User user) {
        this.usersWhoPurchased.add(user);
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

    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

}
