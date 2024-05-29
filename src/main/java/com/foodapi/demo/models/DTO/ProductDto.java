package com.foodapi.demo.models.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    int id;
    String name;
    String description;
    String img;
    BigDecimal price;
    int categoryId;
    int shopId;
    
}
