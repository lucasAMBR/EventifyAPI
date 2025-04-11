package com.equipeAcelera.EventifyAPI.DTOs.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

// DTO que deve ser enviado para criação de post comum
public class CreatePostDTO {
    private int userId;
    private String content;
    private List<MultipartFile> postImages;
    
    public CreatePostDTO(int userId, String content, List<MultipartFile> postImages) {
        this.userId = userId;
        this.content = content;
        this.postImages = postImages;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MultipartFile> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<MultipartFile> postImages) {
        this.postImages = postImages;
    }
    
}
