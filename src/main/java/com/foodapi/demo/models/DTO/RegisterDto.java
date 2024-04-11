package com.foodapi.demo.models.DTO;

import lombok.Data;

@Data
public class RegisterDto {
    private String userName;
    private String email;
    private String password;
}
