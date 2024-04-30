package com.foodapi.demo.models.DTO;



import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostImgDto {
    private String name;
    private String img;
}
