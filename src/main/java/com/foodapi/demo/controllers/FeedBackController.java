package com.foodapi.demo.controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.foodapi.demo.models.FeedBack;
import com.foodapi.demo.models.DTO.FeedBackDto;
import com.foodapi.demo.services.FeedBackService;
import com.foodapi.demo.services.ProductService;
import com.foodapi.demo.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("feedback")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addFeedBack(@RequestParam Integer userId, @RequestParam String content, @RequestParam Integer rateting, @RequestParam Integer productId) {
        FeedBack feedBack = new FeedBack();
        feedBack.setUser(userService.getUserById(userId).orElseThrow());
        feedBack.setContent(content);
        feedBack.setRateing(rateting);
        feedBack.setTime(new Timestamp(System.currentTimeMillis()));
        feedBack.setProduct(productService.getProductById(productId).orElseThrow());
        feedBackService.addFeedBack(feedBack);
        return new ResponseEntity<>("saving", HttpStatus.OK );
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteFeedBack(@RequestParam Integer id) {
        feedBackService.deleteFeedBack(id);
        return new ResponseEntity<>("delete success",HttpStatus.OK );
    }

    @GetMapping("/product/all")
    public ResponseEntity<?> getFeedBackOfProduct(@RequestParam Integer productId) {
        return new ResponseEntity<>(feedBackService.getByProductId(productId), HttpStatus.OK);
    }
    


    

}
