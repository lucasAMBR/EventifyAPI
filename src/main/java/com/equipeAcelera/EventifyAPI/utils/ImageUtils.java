package com.equipeAcelera.EventifyAPI.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.ImageLimitException;

public class ImageUtils {
    public static String saveProfilePic(MultipartFile profilePic) {
        try {
            String uploadDir = "src/main/resources/static/uploads/profile_pic/";
            Files.createDirectories(Paths.get(uploadDir));

            long timestamp = System.currentTimeMillis();

            // Usei um regex para tirar todos os espacos do nome dos arquivos
            String fileName = timestamp +"_"+ profilePic.getOriginalFilename().replaceAll("\\s+", "");
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, profilePic.getBytes());
            String photoUrl = "/uploads/profile_pic/" + fileName;
    
            return photoUrl;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a imagem", e);
        }
    }

    public static List<String> savePostPics(List<MultipartFile> imageList){
        
        if(imageList.size() > 3){
            throw new ImageLimitException("limited for 3 imagem per post!");
        }

        List<String> postPicsPaths = new ArrayList<>();

        for(MultipartFile image : imageList){
            try {
                String uploadDir = "src/main/resources/static/uploads/post_images/";
                Files.createDirectories(Paths.get(uploadDir));
    
                long timestamp = System.currentTimeMillis();
    
                // Usei um regex para tirar todos os espacos do nome dos arquivos
                String fileName = timestamp +"_"+ image.getOriginalFilename().replaceAll("\\s+", "");
                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, image.getBytes());
                String photoUrl = "/uploads/post_images/" + fileName;
                
                postPicsPaths.add(photoUrl);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar a imagem", e);
            }
        }

        return postPicsPaths;
    }

    public static String saveEventBannerPic(MultipartFile image){
        try {
            String uploadDir = "src/main/resources/static/uploads/event_banner/";
            Files.createDirectories(Paths.get(uploadDir));

            long timestamp = System.currentTimeMillis();

            // Usei um regex para tirar todos os espacos do nome dos arquivos
            String fileName = timestamp +"_"+ image.getOriginalFilename().replaceAll("\\s+", "");
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, image.getBytes());
            String photoUrl = "/uploads/event_banner/" + fileName;
    
            return photoUrl;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a imagem", e);
        }       
    }
}
