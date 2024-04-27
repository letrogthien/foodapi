package com.foodapi.demo.models;



import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.mapping.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue()
    private Integer id;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;    

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createAt;

    
 
}
