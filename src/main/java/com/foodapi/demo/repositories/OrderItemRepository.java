package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.OrderItem;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
    
}
