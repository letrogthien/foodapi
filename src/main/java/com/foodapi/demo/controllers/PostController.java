package com.foodapi.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.Post;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.CommentDto;
import com.foodapi.demo.models.DTO.LikeDto;
import com.foodapi.demo.services.AuthenticationService;
import com.foodapi.demo.services.CommentService;
import com.foodapi.demo.services.LikeService;
import com.foodapi.demo.services.PostService;
import com.foodapi.demo.services.UserService;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPost() {
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getMethodName(@RequestParam Integer id) {
        return new ResponseEntity<>(postService.getPostByUserId(id), HttpStatus.OK);
    }

    @GetMapping("comment/all")
    public ResponseEntity<?> getAllCommentByPostId(@RequestParam Integer postId) {
        List<CommentDto> commentDtos = commentService.converDto(commentService.getAllCommentByPostId(postId));
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @GetMapping("like")
    public ResponseEntity<?> sumLikeOfPost(@RequestParam Integer postId) {
        LikeDto likes = new LikeDto(likeService.countLike(postId));
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestParam String title,
            @RequestParam String content) {

        User user = authenticationService.authenticationUser();
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        post.setCreateAt(new Timestamp(System.currentTimeMillis()));
        post.setTitle(title);
        postService.savePost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editPost(@RequestParam String title,
            @RequestParam String content, @RequestParam Integer postId) {
        User user = authenticationService.authenticationUser();

        Post post = postService.getPostByPostId(postId).orElseThrow();
        if (post.getUser().getId() != user.getId()) {
            return new ResponseEntity<>("deny", HttpStatus.FORBIDDEN);
        }
        post.setContent(content);
        post.setTitle(title);
        postService.savePost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestParam Integer postId) {
        User user = authenticationService.authenticationUser();
        Post post = postService.getPostByPostId(postId).orElseThrow();
        if (post.getUser().getId() != user.getId()) {
            return new ResponseEntity<>("deny", HttpStatus.FORBIDDEN);
        }
        commentService.deleteCommentPost(postId);
        likeService.deleteLikePost(postId);
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
