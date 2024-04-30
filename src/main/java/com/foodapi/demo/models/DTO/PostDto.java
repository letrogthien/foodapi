package com.foodapi.demo.models.DTO;

import java.sql.Timestamp;
import java.util.List;

import com.foodapi.demo.models.PostImg;
import com.foodapi.demo.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private int id;
    private int userId;
    private String userName;
    private String title;
    private String content;
    private List<PostImgDto> postImgs;
    private Timestamp createAt;
}
