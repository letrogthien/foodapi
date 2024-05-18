package com.foodapi.demo.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapi.demo.models.Otp;
import java.util.List;


public interface OtpRepository extends JpaRepository<Otp, Integer> {
    void deleteByExpirationBefore(Timestamp systeTimestamp);
    List<Otp> findByOtp(Integer otp);
}
