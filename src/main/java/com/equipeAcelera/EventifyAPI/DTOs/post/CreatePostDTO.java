package com.equipeAcelera.EventifyAPI.DTOs.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.equipeAcelera.EventifyAPI.models.Like.Like;

public class CreatePostDTO {
    private int id;
    private int userId;
    private String content;
    private List<MultipartFile> postImages;
    private List<Like> likeList;
    
    public CreatePostDTO(int id, int userId, String content, List<MultipartFile> postImages, List<Like> likeList) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.postImages = postImages;
        this.likeList = likeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }

    
}
