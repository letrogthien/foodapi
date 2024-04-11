package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapi.demo.models.StatusPay;

public interface StatusPayRepository extends JpaRepository<StatusPay, Integer> {
    
}
