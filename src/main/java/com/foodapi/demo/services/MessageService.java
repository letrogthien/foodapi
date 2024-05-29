package com.foodapi.demo.services;

import java.util.Optional;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.MessageData;
import com.foodapi.demo.models.User;
import com.foodapi.demo.repositories.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Optional<MessageData> getByUser1AndUser2(Integer user1, Integer user2){
        return messageRepository.findByUser1IdAndUser2Id(user1, user2);
    }
    public void saveMessage(MessageData message){
        messageRepository.save(message);
    }
}
