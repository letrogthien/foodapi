package com.foodapi.demo;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.foodapi.demo.repositories.OtpRepository;

import jakarta.transaction.Transactional;

@Component
public class ScheduleOtp {
    @Autowired
    OtpRepository otpRepository;

    @Scheduled(fixedRate = 1*60*1000)
    @Transactional
    public void deleteOtp(){
        otpRepository.deleteByExpirationBefore(new Timestamp(System.currentTimeMillis()));
    }
}


