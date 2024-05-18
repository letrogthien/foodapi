package com.foodapi.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Category;
import com.foodapi.demo.models.QCategory;
import com.foodapi.demo.repositories.CategoryRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class CategoryService {
    @Autowired 
    private CategoryRepository categoryRepository;

    @Autowired 
    private JPAQueryFactory queryFactory;

    private QCategory qCategory= QCategory.category;

    public Optional<Category> getCategoryById(Integer id){
        return categoryRepository.findById(id);
    } 

  

}
