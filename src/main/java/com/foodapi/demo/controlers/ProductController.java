package com.foodapi.demo.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foodapi.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.convertProductToDTO(productService.getAllProducts()), HttpStatus.OK);
    }

    
}
