package com.foodapi.demo.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostImgDto {
    private String name;
    private String url;
}
