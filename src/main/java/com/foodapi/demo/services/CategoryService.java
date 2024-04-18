package com.foodapi.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Category;
import com.foodapi.demo.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired 
    CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(Integer id){
        return categoryRepository.findById(id);
    } 
}
