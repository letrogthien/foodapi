package com.foodapi.demo.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    private final String UPLOAD_DIR = "/tmp";
    private final String ROOT_IMG_LOCATION = "/tmp";

    public String uploadImageService(MultipartFile file) {
        final String[] extend = { "jpg", "png" };
        try {
            if (file == null || file.isEmpty()) {
                return "select file please!";
            }
            String extensionFile = FilenameUtils.getExtension(file.getOriginalFilename());
            boolean contains = Arrays.asList(extend).contains(extensionFile);
            if (!contains) {
                return "not img file";
            }

            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Path checkExistsFile = path.resolve(file.getOriginalFilename());
            if (Files.exists(checkExistsFile)) {
                Random ram = new Random();
                int ran = ram.nextInt(100000);
                Path desPathIfExists = path.resolve(String.valueOf(ran) + file.getOriginalFilename());

                file.transferTo(desPathIfExists);
                return "ok";

            }
            Path desPathIfUnExists = path.resolve(file.getOriginalFilename());
            file.transferTo(desPathIfUnExists);
            return "ok";

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public Resource getImgByUrl(String Url) throws FileNotFoundException {
        try {
            Path file = Paths.get(ROOT_IMG_LOCATION).resolve(Url);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("khong tim thay");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException();
        }

    }

}
