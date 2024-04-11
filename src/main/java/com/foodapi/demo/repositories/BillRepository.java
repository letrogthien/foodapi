package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Bill;
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    
}
