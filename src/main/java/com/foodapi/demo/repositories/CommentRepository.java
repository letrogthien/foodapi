package com.foodapi.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findByPost_Id(Integer postId);

}
