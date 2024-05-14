package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.FlashSale;

@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale,Integer> {
    
    
}
