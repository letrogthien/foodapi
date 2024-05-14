package com.foodapi.demo.controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.foodapi.demo.models.Comment;
import com.foodapi.demo.models.User;
import com.foodapi.demo.services.AuthenticationService;
import com.foodapi.demo.services.CommentService;
import com.foodapi.demo.services.PostService;
import com.foodapi.demo.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<?> addCommnet(@RequestParam Integer postId, @RequestParam String content) {
        User user = authenticationService.authenticationUser();

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(postService.getPostByPostId(postId).orElseThrow());
        comment.setCreateAt(new Timestamp(System.currentTimeMillis()));
        comment.setContent(content);
        commentService.addComment(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam Integer commentId) {
        User user = authenticationService.authenticationUser();

        Comment comment = commentService.getCommentById(commentId).orElseThrow();
        if (comment.getUser().getId() != user.getId()) {
            return new ResponseEntity<>("deny", HttpStatus.FORBIDDEN);
        }
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editComment(@RequestParam Integer commnetId, @RequestParam String content) {
        User user = authenticationService.authenticationUser();

        Comment comment = commentService.getCommentById(commnetId).orElseThrow();
        if (comment.getUser().getId() != user.getId()) {
            return new ResponseEntity<>("deny", HttpStatus.FORBIDDEN);
        }
        comment.setContent(content);
        commentService.addComment(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
