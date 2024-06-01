package com.foodapi.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.User;
import com.foodapi.demo.services.AuthenticationService;
import com.foodapi.demo.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("user")
public class UserController {
       @Autowired
       UserService userService;

       @Autowired
       AuthenticationService authenticationService;

       @Autowired
       PasswordEncoder passwordEncoder;

       @GetMapping("/profile")
       public ResponseEntity<?> getMethodName(@RequestParam(required = false) Integer id) {
              User user = userService.getUserById(id).orElseThrow();
              return new ResponseEntity<>(user, HttpStatus.OK);
       }

       @PostMapping("/edit")
       public ResponseEntity<?> editProfile(@RequestParam(required = false) String password,
                     @RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
              Integer userId = authenticationService.authenticationUser().getId();
              User user = userService.getUserById(userId).orElseThrow();
              if (password != null && !password.isEmpty()) {
                     user.setPassword(passwordEncoder.encode(password));
              }
              if (email != null) {
                     user.setEmail(email);
              }
              if (phone != null) {
                     user.setPhone(phone);
              }
              userService.saveUser(user);

              return new ResponseEntity<>("ok", HttpStatus.OK);
       }

       @GetMapping("/search")
       public ResponseEntity<?> getMethodName(@RequestParam String string) {
           return new ResponseEntity<>(userService.getUserByUserNameOrEmail(string),HttpStatus.OK);
       }
       
       

}
