package com.foodapi.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Comment;
import com.foodapi.demo.models.DTO.CommentDto;
import com.foodapi.demo.repositories.CommentRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    public List<Comment> getAllCommentByPostId(Integer postId){
        return commentRepository.findByPost_Id(postId);
    }

    public Optional<Comment> getCommentById(Integer commentId){
        return commentRepository.findById(commentId);
    }


    public Comment deleteComment(Integer id){
        Comment comment=commentRepository.findById(id).orElseThrow();
        commentRepository.deleteById(id);
        return comment;
    }

    public boolean deleteCommentPost(Integer postId){
        return commentRepository.deleteByPost_Id(postId);
    }

    public Comment editComment(String content, Integer id){
        Comment comment= commentRepository.findById(id).orElseThrow();
        comment.setContent(content);
        commentRepository.save(comment);
        return comment;
    }


    public Comment addComment(Comment comment){
        return commentRepository.save(comment);
    }

    

    public List<CommentDto> converDto(List<Comment> comments){
        return comments.stream()
                        .map(comment -> new CommentDto(
                            comment.getId(),
                            comment.getUser().getId(),
                            comment.getPost().getId(),
                            comment.getContent(),
                            comment.getCreateAt()
                        ) )
                        .collect(Collectors.toList());
    }


}
