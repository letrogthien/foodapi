package com.foodapi.demo.socket;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer senderId;
    private Integer receiverId;
    private String message;
}
