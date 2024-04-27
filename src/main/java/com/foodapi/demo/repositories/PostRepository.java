package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Post;
import com.foodapi.demo.models.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser_Id(Integer id);
    
}
