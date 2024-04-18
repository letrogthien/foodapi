package com.foodapi.demo.models.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    int id;
    String name;
    String desciption;
    String img;
    BigDecimal price;
    int categoryId;
    int shopId;
}
