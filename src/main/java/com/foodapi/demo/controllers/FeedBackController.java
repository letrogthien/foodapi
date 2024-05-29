package com.foodapi.demo.controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.foodapi.demo.models.FeedBack;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.FeedBackDto;
import com.foodapi.demo.services.AuthenticationService;
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

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<?> addFeedBack(@RequestParam String content, @RequestParam Integer rateting, @RequestParam Integer productId) {
        User user = authenticationService.authenticationUser();
        FeedBack feedBack = new FeedBack();
        feedBack.setUser(user);
        feedBack.setContent(content);
        feedBack.setRateing(rateting);
        feedBack.setTime(new Timestamp(System.currentTimeMillis()));
        feedBack.setProduct(productService.getProductById(productId).orElseThrow());
        feedBackService.addFeedBack(feedBack);
        return new ResponseEntity<>("saving", HttpStatus.OK );
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteFeedBack(@RequestParam Integer id) {
        User user = authenticationService.authenticationUser();
        FeedBack feedBack = feedBackService.getByFeedBackId(id);
        if(feedBack.getUser().getId() != user.getId()){
            return new ResponseEntity<>("denied", HttpStatus.UNAUTHORIZED);
        }
        feedBackService.deleteFeedBack(id);
        return new ResponseEntity<>("delete success",HttpStatus.OK );
    }

    @GetMapping("/product/all")
    public ResponseEntity<?> getFeedBackOfProduct(@RequestParam Integer productId) {
        return new ResponseEntity<>(feedBackService.getByProductId(productId), HttpStatus.OK);
    }
    
    @GetMapping("/comment/sum")
    public ResponseEntity<?> sumComment1(@RequestParam Integer productId) {
        return new ResponseEntity<>(feedBackService.sumComment(productId), HttpStatus.OK);
    }


    

}
