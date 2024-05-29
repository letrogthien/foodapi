package com.foodapi.demo.socket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatService {
    @Autowired
    private ObjectMapper oMapper; 



    public File createFileIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public List<Message> readMessagesFromFile(File file) throws IOException {
        if (file.exists() && file.length() > 0) {
            return oMapper.readValue(file, new TypeReference<List<Message>>() {});
        } else {
            return new ArrayList<>();
        }
    }
}
