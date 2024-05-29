package com.foodapi.demo.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name = "totalamount")
    private BigDecimal totalAmount;

    @CreationTimestamp
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "paymentmethod")
    private Payment paymentMethod;
    
    @OneToOne
    @JoinColumn(name = "status")
    private StatusPay statusPay;
}
