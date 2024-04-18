package com.foodapi.demo.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.exceptions.User.UserAlreadyExist;
import com.foodapi.demo.jwt.JwtUtil;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.LoginDto;
import com.foodapi.demo.models.DTO.RegisterDto;
import com.foodapi.demo.services.UserService;

import jakarta.annotation.security.PermitAll;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(), loginDto.getPassword()));
        User usOptional= userService.getUserByUserNameOrEmail(loginDto.getUserNameOrEmail()).orElse(null);
        if (usOptional!=null){
            usOptional.setLastLogin(new Timestamp(System.currentTimeMillis()));
            userService.saveUser(usOptional);
        }
        String token = jwtUtil.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {

        if (userService.exitUserName(registerDto.getUserName())) {
            throw new UserAlreadyExist(registerDto.getUserName());
        }
        if (userService.exitEmail(registerDto.getEmail())) {
            throw new UserAlreadyExist(registerDto.getEmail());
        }
        User user = new User();
        user.setUsername(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setActive(true);
        user.setEmail(registerDto.getEmail());
        user.setRegistDate(new Timestamp(System.currentTimeMillis()));
        userService.defaultRole(user);
        userService.saveUser(user);
        return new ResponseEntity<>("register Succes", HttpStatus.OK);
    }

    
    @GetMapping("/test")   
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("testtt", HttpStatus.OK);
    }

}
