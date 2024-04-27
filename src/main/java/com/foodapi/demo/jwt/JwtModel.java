package com.foodapi.demo.jwt;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtModel {
    private String jwt;
    private Date expired;
}
