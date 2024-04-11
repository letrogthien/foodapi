package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    
}
