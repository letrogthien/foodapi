package com.foodapi.demo.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "totalprice")
    private BigDecimal totalPrice;

    private Timestamp date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private StatusOrder statusOrder;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true,cascade =  CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY, orphanRemoval = true,cascade =  CascadeType.ALL)
    private Bill bill;
    
}
