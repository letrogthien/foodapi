package com.foodapi.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Order;
import com.foodapi.demo.models.OrderItem;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.QOrderItem;
import com.foodapi.demo.models.DTO.OrderItemDto;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.repositories.OrderItemRepository;
import com.foodapi.demo.repositories.OrderRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    public List<OrderItem> getOrderItemOfOrder(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public List<OrderItemDto> convertListProductToDTO(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> new OrderItemDto(
                        orderItem.getId(),
                        orderItem.getOrder().getId(),
                        orderItem.getProduct().getId(),
                        orderItem.getPriceBuy(),
                        orderItem.getQuantity()))
                .collect(Collectors.toList());
    }

    public OrderItemDto convertProductToDTO(OrderItem orderItem) {
        return new OrderItemDto(
            orderItem.getId(),
            orderItem.getOrder().getId(),
            orderItem.getProduct().getId(),
            orderItem.getPriceBuy(),
            orderItem.getQuantity());
    }

    public List<Product> getListProductId() {
        QOrderItem qOrderItem = QOrderItem.orderItem;
        return queryFactory.select(qOrderItem.product)
                .from(qOrderItem)
                .groupBy(qOrderItem.product.id)
                .orderBy(qOrderItem.quantity.sum().desc())
                .limit(5)
                .fetch();
    }
}
