package com.foodapi.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.QOrderItem;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Service
public class OrderItemService {
    


    
    @Autowired
    private JPAQueryFactory queryFactory;

    
    
    

    public List<Product> getListProductId(){
        QOrderItem qOrderItem= QOrderItem.orderItem;
        return queryFactory.select(qOrderItem.product)
                            .from(qOrderItem)
                            .groupBy(qOrderItem.product.id)
                            .orderBy(qOrderItem.quantity.sum().desc())
                            .limit(2)
                            .fetch();
    }
}
