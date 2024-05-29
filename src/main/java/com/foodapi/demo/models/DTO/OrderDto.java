package com.foodapi.demo.models.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
   
   
    private  Integer id;

    private Integer userId;
    
    private BigDecimal totalPrice;

    private Timestamp date;

    private Integer statusOrder;

    private String address;

    private Integer shopId;
    

}
