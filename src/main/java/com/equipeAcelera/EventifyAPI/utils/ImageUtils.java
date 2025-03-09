package com.equipeAcelera.EventifyAPI.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

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
}
