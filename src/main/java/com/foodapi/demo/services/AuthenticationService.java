package com.foodapi.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.User;

@Service
public class AuthenticationService {
    @Autowired
    UserService userService;

    public User authenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserNameOrEmail(authentication.getName()).orElseThrow();
        return user;
    }
}
