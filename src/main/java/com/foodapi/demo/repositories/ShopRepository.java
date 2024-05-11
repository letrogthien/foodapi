package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByUser(User user);
    List<Shop> findByUser_Id(Integer user_Id);

    boolean existsByUser(User user);
}
