package com.foodapi.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer>, QuerydslPredicateExecutor {
   
    List<Product> findByNameLikeIgnoreCase(String name);
    List<Product> findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Integer categoryId);
    List<Product> searchByNameContaining(String name);

    List<Product> findByCategory_Id(Integer id);
    Optional<Product> findById(Integer id);
    
    void deleteByName(String name);
    List<Product> findByShopId(Integer shopId);

    
    List<Product> findByNameContainingIgnoreCaseAndPriceBetween(String name, Double priceStart, Double priceEnd);

    

    
}
