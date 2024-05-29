package com.foodapi.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Order;
import com.foodapi.demo.models.StatusOrder;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, QuerydslPredicateExecutor{
    List<Order> findByShopId(Integer shopId);
    List<Order> findByStatusOrder(StatusOrder statusOrder);
    
}
