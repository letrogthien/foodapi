package com.foodapi.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.foodapi.demo.services.UploadService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class FileUpLoadController {
    @Autowired
    UploadService uploadService;

    @Autowired
    HttpServletRequest httpServletRequest;


    @PostMapping("/upload/img")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        String run = uploadService.uploadImageService(file);
        if (!run.equalsIgnoreCase("ok")) {
            return new ResponseEntity<>(run, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(run, HttpStatus.OK);

    }

    @GetMapping("/get/img/{stringImg}")
public ResponseEntity<?> getMethodName(@PathVariable String stringImg) throws FileNotFoundException {
    Resource resource = uploadService.getImgByUrl(stringImg);

    String contentType;
    try {
        contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
        contentType = "application/octet-stream";
    }
    return ResponseEntity.ok()
                         .contentType(MediaType.parseMediaType(contentType))
                         .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + resource.getFilename() + "\"") // Tùy chỉnh lại nếu cần inline
                         .body(resource);
}

}
