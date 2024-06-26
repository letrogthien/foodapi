package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.FeedBack;
import java.util.List;
import java.util.Optional;


@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
    Optional<FeedBack> findById(Integer id);
    List<FeedBack> findByUser_Id(Integer userId);
    List<FeedBack> findByProduct_Id(Integer productId);
    long countByProduct_Id(Integer productId);
    
}
