package com.foodapi.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String UsernameOrEmail,String userNameOrEmail);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    
    
}
