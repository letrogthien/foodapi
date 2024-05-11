package com.foodapi.demo.models.DTO;

import java.sql.Timestamp;

import com.foodapi.demo.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackDto {
 
    private Integer id;


  
    private Integer userId;

    
    private Timestamp time;

    private String content;

    private Integer rateing;

 
    private Integer productId;
}
