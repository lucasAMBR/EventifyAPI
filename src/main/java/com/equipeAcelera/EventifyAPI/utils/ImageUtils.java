package com.equipeAcelera.EventifyAPI.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.ImageLimitException;

public class ImageUtils {
    public static String saveProfilePic(MultipartFile profilePic) {
        try {
            String uploadDir = "src/main/resources/static/uploads/profile_pic/";
            Files.createDirectories(Paths.get(uploadDir));

            long timestamp = System.currentTimeMillis();
            String fileName = timestamp + "_" + profilePic.getOriginalFilename().replaceAll("\\s+", "");
            String filePath = uploadDir + fileName;
            File outputFile = new File(filePath);

            BufferedImage originalImage = ImageIO.read(profilePic.getInputStream());
            BufferedImage croppedImage = cropToSquare(originalImage, 300);
            ImageIO.write(croppedImage, "jpg", outputFile);

            return "/uploads/profile_pic/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a imagem", e);
        }
    }

    private static BufferedImage cropToSquare(BufferedImage source, int size) {
        int width = source.getWidth();
        int height = source.getHeight();
        int minSize = Math.min(width, height);

        BufferedImage cropped = source.getSubimage(
            (width - minSize) / 2, 
            (height - minSize) / 2, 
            minSize, 
            minSize
        );

        BufferedImage resized = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(cropped, 0, 0, size, size, null);
        g2d.dispose();

        return resized;
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
