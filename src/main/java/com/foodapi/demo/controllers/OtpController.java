package com.foodapi.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.services.OtpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping("/get")
    public ResponseEntity<?> getOtp(@RequestParam String email) {
        Integer otpCode = otpService.createOtp(email);
        return new ResponseEntity<>(otpCode, HttpStatus.OK);
    }
    
}
