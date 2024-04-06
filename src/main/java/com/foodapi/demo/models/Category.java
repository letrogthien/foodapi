package com.foodapi.demo.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "categories")
public class Category {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 50)
    private String name;

    // Quan hệ one-to-many với bảng products
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // Cái category này là thuộc tính trong class Product
    private Set<Product> products = new HashSet<>();
    
}
