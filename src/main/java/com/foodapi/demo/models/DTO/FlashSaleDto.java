package com.foodapi.demo.models.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashSaleDto {
    private int id;
    private String name;
    private String description;
    private String img;
    private BigDecimal price;
    private BigDecimal newPrice;
    private int categoryId;
    private int shopId;
}
