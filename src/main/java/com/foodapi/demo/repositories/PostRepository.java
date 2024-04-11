package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
}
