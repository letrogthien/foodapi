package com.foodapi.demo.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Order;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.QOrder;
import com.foodapi.demo.models.StatusOrder;
import com.foodapi.demo.models.DTO.OrderDto;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.repositories.OrderRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class OrderService {
    @Autowired 
    private OrderRepository orderRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QOrder qOrder = QOrder.order;

    public List<Order> getOrderByShop(Integer shopId){
        return orderRepository.findByShopId(shopId);
    }


    public List<OrderDto> getOrderByShopTime(Integer shopId, Long day){
        return jpaQueryFactory.select(Projections.constructor(OrderDto.class, qOrder.id,qOrder.user.id,qOrder.totalPrice,qOrder.date,qOrder.statusOrder.id,qOrder.address,qOrder.shop.id))
                                .from(qOrder)
                                .where(qOrder.date.after(new Timestamp(System.currentTimeMillis()-day*24*60*60*1000)), qOrder.shop.id.eq(shopId))
                                .fetch();
    }


    public List<OrderDto> getOrderByStatus(StatusOrder statusOrder){
        return convertListProductToDTO(orderRepository.findByStatusOrder(statusOrder));
    }

    public Optional<Order> getOrderById(Integer id){
        return orderRepository.findById(id);
    }

    public Order changeOrderStatus(StatusOrder statusOrder, Order order ){
        order.setStatusOrder(statusOrder);
        return orderRepository.save(order);
    }
    
    public List<OrderDto> convertListProductToDTO(List<Order> list) {
        return list.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getUser().getId(),
                        order.getTotalPrice(),
                        order.getDate(),
                        order.getStatusOrder().getId(),
                        order.getAddress(),
                        order.getShop().getId()
                ))
                        
                .collect(Collectors.toList());
    }

    public OrderDto convertProductToDTO(Order order) {
        return new OrderDto(
            order.getId(),
            order.getUser().getId(),
            order.getTotalPrice(),
            order.getDate(),
            order.getStatusOrder().getId(),
            order.getAddress(),
            order.getShop().getId()
    );
    }


}
