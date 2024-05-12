package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, QuerydslPredicateExecutor{
    
}
