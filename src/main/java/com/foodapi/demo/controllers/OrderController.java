package com.foodapi.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.Order;
import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.StatusOrder;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.OrderDto;
import com.foodapi.demo.models.DTO.OrderItemDto;
import com.foodapi.demo.services.AuthenticationService;
import com.foodapi.demo.services.OrderItemService;
import com.foodapi.demo.services.OrderService;
import com.foodapi.demo.services.ShopService;
import com.foodapi.demo.services.StatusOrderService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    @Autowired
    private StatusOrderService statusOrderService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ShopService shopService;

    
    @GetMapping("/shop/all")
    public ResponseEntity<?> getOrderShopTime(@RequestParam Integer shopId, @RequestParam Long day) {
        return new ResponseEntity<>(orderService.getOrderByShopTime(shopId, day),HttpStatus.OK);
    }


    @GetMapping("/shop/status")
    public ResponseEntity<?> getOrderByStatus(@RequestParam Integer shopId, @RequestParam Integer statusId) {
        StatusOrder statusOrder = statusOrderService.getStatusOrderById(statusId).orElseThrow();
        List<OrderDto> orderDtos = orderService.getOrderByStatus(statusOrder); 
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @PostMapping("status/edit")
    public ResponseEntity<?> changeStatusOfOrder (@RequestParam Integer statusId,@RequestParam Integer orderId) {
        Order order = orderService.getOrderById(orderId).orElseThrow();
        StatusOrder statusOrder = statusOrderService.getStatusOrderById(statusId).orElseThrow();
        User user = authenticationService.authenticationUser();
        Shop shop = shopService.getShopByUser(user).orElseThrow();
        if (shop.getId()!=order.getShop().getId()) {
            return new ResponseEntity<>("don hang khong phai cua ban", HttpStatus.FORBIDDEN);
        } 
        orderService.changeOrderStatus(statusOrder, order);
        String response = "change status to" + statusOrder.getName();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/customer/comfirm")
    public ResponseEntity<?> customerComfirmOrder(@RequestParam Integer orderId) {
        Order order = orderService.getOrderById(orderId).orElseThrow();
        User user = authenticationService.authenticationUser();
        if (user.getId() != order.getUser().getId()) {
            return new ResponseEntity<>("not u", HttpStatus.FORBIDDEN);
        }
        if (order.getStatusOrder().getId() != 5) {
            return new ResponseEntity<>("ban k co quyen", HttpStatus.FORBIDDEN);
        }
        StatusOrder statusOrder = statusOrderService.getStatusOrderById(3).orElseThrow();
        orderService.changeOrderStatus(statusOrder, order);
        String response = "change status to" + statusOrder.getName();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody Integer waiting) {
        //TODO: process POST request
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    

    @GetMapping("item")
    public ResponseEntity<?> getOrderItem(@RequestParam Integer orderId) {

        List<OrderItemDto> dtos = orderItemService.convertListProductToDTO(orderItemService.getOrderItemOfOrder(orderId));
        return new ResponseEntity<>(dtos, HttpStatus.OK) ;
    }
    
    
    
}