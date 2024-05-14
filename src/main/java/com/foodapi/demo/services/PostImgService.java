package com.foodapi.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.PostImg;
import com.foodapi.demo.models.DTO.PostImgDto;
import com.foodapi.demo.repositories.PostImgRepository;

@Service
public class PostImgService {
    
    @Autowired
    PostImgRepository postImgRepository;

    @Autowired
    UploadService uploadService;

    public List<PostImgDto> getPostImgByPostId(int id){
        return postImgRepository.findByPostId(id).stream()
                    .map(a-> new PostImgDto(a.getName(), a.getUrl()) )
                    .collect(Collectors.toList());
    }

    public void savePostImg(PostImg postImg){
        postImgRepository.save(postImg);
    }

    // public List<PostImgDto> getPostImg(int id){
    //     List<PostImg> postImgs=this.getPostImgByPostId(id);
    //     List<PostImgDto> postImgDtos= postImgs.stream()
    //                     .map(postImg -> new PostImgDto(postImg.getName(),
    //                                                     uploadService.getImgByUrl(postImg.getUrl())))
    //                     .collect(Collectors.toList());
    //     return postImgDtos;
    // }
}
