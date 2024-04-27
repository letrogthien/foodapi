package com.foodapi.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Like;
import com.foodapi.demo.repositories.LikeRepository;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    public List<Like> getAllLike(){
        return likeRepository.findAll(); 
    }

    public Like deleteLike(Integer id){
        Like like=likeRepository.findById(id).orElseThrow();
        likeRepository.deleteById(id);
        return like;
    }

    public Like addLike(Like like){
        return likeRepository.save(like);
    }

    public Integer countLike(Integer postId){
        return likeRepository.countByPost_Id(postId);
    }
}
