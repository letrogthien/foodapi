package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Product;
import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameLikeIgnoreCase(String name);
    List<Product> findByName(String name);
    
   
}
