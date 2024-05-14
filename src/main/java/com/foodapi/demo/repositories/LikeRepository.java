package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Like;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
        Integer countByPost_Id(Integer postId);
        Optional<Like> findByPostIdAndUserId(Integer postId, Integer userId);
        boolean deleteByPost_Id(Integer postId);
}
