package com.foodapi.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
        
    }

    public List<ProductDto> convertProductToDTO(List<Product> products){
        return products.stream()
                        .map(product->
                            new ProductDto(product.getId(), product.getName(),product.getDescription(),product.getImg(),product.getPrice(),product.getCategory().getId(),product.getShop().getId())
                        )
                        .collect(Collectors.toList());
    }
}
