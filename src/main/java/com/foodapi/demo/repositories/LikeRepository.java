package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Like;
@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    
}
