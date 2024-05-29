package com.foodapi.demo.socket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapi.demo.models.MessageData;
import com.foodapi.demo.services.MessageService;
import com.foodapi.demo.services.UserService;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ObjectMapper oMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverId().toString(), "/private", message);
        System.out.println(message.toString());

        try {
            MessageData message2 = messageService
                    .getByUser1AndUser2(message.getSenderId(), message.getReceiverId()).orElse(null);
            MessageData message3 = messageService
                    .getByUser1AndUser2(message.getReceiverId(), message.getSenderId()).orElse(null);
            if (message2 != null) {
                File jsonFile = new File("/tmp/"+message2.getFilename());
                List<Message> messages = chatService.readMessagesFromFile(jsonFile);
                messages.add(message);
                oMapper.writeValue(jsonFile, messages);
            }
            if (message3 != null) {
                File jsonFile = new File("/tmp/"+message2.getFilename());
                List<Message> messages = chatService.readMessagesFromFile(jsonFile);
                messages.add(message);
                oMapper.writeValue(jsonFile, messages);
            }

            if (message2 == null && message3 == null) {
                MessageData newMessageData = new MessageData();
                String filename = message.getSenderId().toString() + "_" + message.getReceiverId().toString() + ".json";
                File jsonFile = chatService.createFileIfNotExists("/tmp/" + filename);
                List<Message> messages = new ArrayList<>();
                messages.add(message);
                oMapper.writeValue(jsonFile, messages);
                newMessageData.setUser1(
                        userService.getUserById(message.getSenderId()).orElseThrow());
                newMessageData.setUser2(
                        userService.getUserById(message.getReceiverId()).orElseThrow());
                newMessageData.setFilename(filename);

                messageService.saveMessage(newMessageData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

}
