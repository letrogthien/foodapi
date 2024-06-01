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
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.OrderItemDto;
import com.foodapi.demo.models.DTO.OrderItemResponse;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.repositories.OrderItemRepository;
import com.foodapi.demo.repositories.OrderRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    QOrderItem qOrderItem = QOrderItem.orderItem;

    public List<OrderItem> getOrderItemOfOrder(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
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

    public List<OrderItemResponse> getAllOrderByUser(User user) {
        return queryFactory
                .select(Projections.constructor(OrderItemResponse.class,
                        qOrderItem.id,
                        qOrderItem.order.id,
                        Projections.constructor(ProductDto.class,
                                qOrderItem.product.id, qOrderItem.product.name, qOrderItem.product.description,
                                qOrderItem.product.img, qOrderItem.product.price, qOrderItem.product.category.id,
                                qOrderItem.product.shop.id),
                        qOrderItem.priceBuy,
                        qOrderItem.quantity,
                        qOrderItem.order.statusOrder.id))
                .from(qOrderItem)
                .join(qOrderItem.order).on(qOrderItem.order.user.id.eq(user.getId()))
                .fetch();
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
