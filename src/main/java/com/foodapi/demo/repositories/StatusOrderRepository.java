package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.StatusOrder;


@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrder, Integer> {
    
}
