package com.foodapi.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodapi.demo.models.Like;
import com.foodapi.demo.models.DTO.LikeDto;
import com.foodapi.demo.services.LikeService;
import com.foodapi.demo.services.PostService;
import com.foodapi.demo.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("like")
public class LikeController {
    @Autowired 
    LikeService likeService;

    @Autowired 
    UserService userService;

    @Autowired
    PostService postService;

    @PostMapping("liking")    
    public ResponseEntity<?> likeForPost(@RequestParam Integer userId, @RequestParam Integer postId ){
        Like like = new Like();
        like.setPost(postService.getPostByPostId(postId).orElseThrow());
        like.setUser(userService.getUserById(userId).orElseThrow());
        likeService.Liking(like);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    
    
}
