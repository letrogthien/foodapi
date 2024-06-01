package com.foodapi.demo.models.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Integer id;

    private Integer orderId;


    private ProductDto productDto;

 
    private BigDecimal priceBuy;

    private Integer quantity;

    private Integer status;
}
