package com.foodapi.demo.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    private String userNameOrEmail;
    private String password;
}
