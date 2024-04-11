package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
 
}
