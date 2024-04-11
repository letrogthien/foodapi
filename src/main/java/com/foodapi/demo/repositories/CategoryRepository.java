package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
