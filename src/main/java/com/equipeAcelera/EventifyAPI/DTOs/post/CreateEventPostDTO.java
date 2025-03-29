package com.equipeAcelera.EventifyAPI.DTOs.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

// DTO para criar uma postagem no feed de um evento
public class CreateEventPostDTO {
    private int userId;
    private int eventId;
    private String content;
    private List<MultipartFile> postImages;
    
    public CreateEventPostDTO(int userId, int eventId, String content, List<MultipartFile> postImages) {
        this.userId = userId;
        this.eventId = eventId;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    
}
