package com.foodapi.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.User;
import com.foodapi.demo.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("user")
public class UserController {
       @Autowired
       UserService userService;

       @GetMapping("/profile")
       public ResponseEntity<?> getMethodName(@RequestParam(required = false) Integer id) {
        User user = userService.getUserById(id).orElseThrow();
           return new ResponseEntity<>(user, HttpStatus.OK);
       }
       
}

