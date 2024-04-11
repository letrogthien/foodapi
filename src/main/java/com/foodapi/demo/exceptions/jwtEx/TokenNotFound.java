package com.foodapi.demo.exceptions.jwtEx;

public class TokenNotFound extends RuntimeException {
    public TokenNotFound (String message){
        super(message);
    }
}
