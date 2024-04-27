package com.foodapi.demo.models.DTO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private String content;
    private Timestamp createAt;
}
