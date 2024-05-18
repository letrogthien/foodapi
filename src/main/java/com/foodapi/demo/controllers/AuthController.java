package com.foodapi.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.exceptions.User.UserAlreadyExist;
import com.foodapi.demo.jwt.JwtModel;
import com.foodapi.demo.jwt.JwtUtil;
import com.foodapi.demo.models.Otp;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.JwtResponse;
import com.foodapi.demo.models.DTO.LoginDto;
import com.foodapi.demo.models.DTO.RegisterDto;
import com.foodapi.demo.services.OtpService;
import com.foodapi.demo.services.UserService;

import jakarta.annotation.security.PermitAll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private OtpService otpService;

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
        JwtResponse jwtResponse= new JwtResponse(token,userService.getIdByUserName(loginDto.getUserNameOrEmail()), jwtUtil.getExpiration(token));
        
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam Integer otp) {

        if (userService.exitUserName(username)) {
            throw new UserAlreadyExist(username);
        }
        if (userService.exitEmail(email)) {
            throw new UserAlreadyExist(email);
        }
        List<Otp> otps = otpService.getByOtp(otp);
        if (!otpService.checkOtp(otps, email)) {
            return new ResponseEntity<>("OTP not true", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setEmail(email);
        user.setRegistDate(new Timestamp(System.currentTimeMillis()));
        userService.defaultRole(user);
        userService.saveUser(user);
        return new ResponseEntity<>("register Succes", HttpStatus.OK);
    }
    @GetMapping("/test")
    public String getMethodName() {
        return "hi";
    }
    

    
    

}
