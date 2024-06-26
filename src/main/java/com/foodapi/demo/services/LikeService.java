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



    public Like Liking(Like like){
        Like likeNew =likeRepository.findByPostIdAndUserId(like.getPost().getId(),like.getUser().getId()).orElse(null);
        if ( likeNew == null){
            return likeRepository.save(like);
        }
        
        return deleteLike(likeNew.getId());
    }

    public Integer countLike(Integer postId){
        return likeRepository.countByPost_Id(postId);
    }

    public void deleteLikePost(Integer postId){
         likeRepository.deleteByPostId(postId);
    }
}
