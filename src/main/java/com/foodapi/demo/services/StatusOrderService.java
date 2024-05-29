package com.foodapi.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.StatusOrder;
import com.foodapi.demo.repositories.StatusOrderRepository;

import ch.qos.logback.core.status.Status;

@Service
public class StatusOrderService {
    @Autowired
    private StatusOrderRepository statusOrderRepository;


    public Optional<StatusOrder> getStatusOrderById(Integer id){
        return statusOrderRepository.findById(id);
    }
}
