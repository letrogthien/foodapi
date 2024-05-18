package com.foodapi.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get/shop")
    public ResponseEntity<?> getCategoryByShopId(@RequestParam Integer shopId) {
        return new ResponseEntity<>(categoryService.getCategoryById(shopId), HttpStatus.OK);
    }
    
}
