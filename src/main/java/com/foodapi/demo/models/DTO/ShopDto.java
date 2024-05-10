package com.foodapi.demo.models.DTO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopDto {
    
    private Integer id;

    private String name;

   
    private Timestamp regisDate;

    private Integer userId;
    
}
