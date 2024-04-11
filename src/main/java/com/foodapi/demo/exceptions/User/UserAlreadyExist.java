package com.foodapi.demo.exceptions.User;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String userNameOrEmail){
        super("user already exist :"+userNameOrEmail );
    }
}
