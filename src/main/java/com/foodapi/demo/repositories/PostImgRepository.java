package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.PostImg;
import java.util.List;


@Repository
public interface PostImgRepository extends JpaRepository<PostImg, Integer>{
    List<PostImg> findByPostId(int postId);
    
} 