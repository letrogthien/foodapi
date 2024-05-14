package com.foodapi.demo.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodapi.demo.models.Post;
import com.foodapi.demo.models.DTO.PostDto;
import com.foodapi.demo.models.DTO.PostImgDto;
import com.foodapi.demo.repositories.PostRepository;

@Service
public class PostService {
    @Autowired 
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    PostImgService postImgService;
    public List<PostDto> getAllPost(){
        List<Post> posts = postRepository.findAll();
        
        return posts.stream()
                    .map(post -> new PostDto(
                        post.getId(),
                        post.getUser().getId(),
                        post.getUser().getUsername(),
                        post.getTitle(),
                        post.getContent(),
                        postImgService.getPostImgByPostId(post.getId()),
                        post.getCreateAt()
                    ) )
                    .collect(Collectors.toList());
    }
   
    public List<PostDto> getPostByUserId(int id){
        List<Post> posts= postRepository.findByUser_Id(id);
        return posts.stream()
                    .map(post -> new PostDto(
                        post.getId(),
                        post.getUser().getId(),
                        post.getUser().getUsername(),
                        post.getTitle(),
                        post.getContent(),
                        postImgService.getPostImgByPostId(post.getId()),
                        post.getCreateAt()
                    ) )
                    .collect(Collectors.toList());

    }

    public Optional<Post> getPostByPostId(int id){
        return postRepository.findById(id);

    }

    public void deletePost(Integer id){
        postRepository.deleteById(id);
    }

    public Post addPost(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setCreateAt(new Timestamp(System.currentTimeMillis()));
        post.setTitle(postDto.getTitle());
        post.setUser(userService.getUserById(postDto.getUserId()).orElseThrow());
        return postRepository.save(post);
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }

}
