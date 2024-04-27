package com.foodapi.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.PostImg;
import com.foodapi.demo.repositories.PostImgRepository;

@Service
public class PostImgService {
    @Autowired
    PostImgRepository postImgRepository;

    public List<PostImg> getPostImgByPostId(int id){
        return postImgRepository.findByPostId(id);
    }
}
