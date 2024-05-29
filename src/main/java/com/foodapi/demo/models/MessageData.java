package com.foodapi.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "message")
public class MessageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @ManyToOne
    @JoinColumn(name = "user1", referencedColumnName = "id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2", referencedColumnName = "id")
    private User user2;

    private String filename;
}
