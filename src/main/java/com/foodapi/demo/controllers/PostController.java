package com.foodapi.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.DTO.CommentDto;
import com.foodapi.demo.models.DTO.LikeDto;
import com.foodapi.demo.services.CommentService;
import com.foodapi.demo.services.LikeService;
import com.foodapi.demo.services.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired 
    CommentService commentService;
    @Autowired
    LikeService likeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPost() {
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getMethodName(@RequestParam Integer id) {
        return new ResponseEntity<>(postService.getPostByUserId(id), HttpStatus.OK);
    }
    
    @GetMapping("comment/all")
    public ResponseEntity<?> getAllCommentByPostId(@RequestParam Integer postId){
        List<CommentDto> commentDtos = commentService.converDto(commentService.getAllCommentByPostId(postId));
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @GetMapping("like")
    public ResponseEntity<?> sumLikeOfPost(@RequestParam Integer postId){
        LikeDto likes = new LikeDto(likeService.countLike(postId));
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    
}
