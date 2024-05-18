package com.foodapi.demo.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Otp;
import com.foodapi.demo.repositories.OtpRepository;

@Service
public class OtpService {
    @Autowired
    private OtpRepository otpRepository;

    public Integer createOtp(String email){
        Random ram = new Random();
        Integer otpCode = ram.nextInt(100000,999999);
        Otp otp = new Otp(); 
        otp.setEmail(email);
        otp.setExpiration(new Timestamp(System.currentTimeMillis()+1000*60*10));
        otp.setOtp(otpCode);
        otpRepository.save(otp);
        return otpCode;
    }

    public List<Otp> getByOtp(Integer otp){
        return otpRepository.findByOtp(otp);
    }

    public boolean checkOtp(List<Otp> otps, String email){
        for (Otp otp : otps) {
            if (otp.getEmail().equals(email) && otp.getExpiration().after(new Timestamp(System.currentTimeMillis()))) {
                return true;
            }
        }
        return false;
    }
}
