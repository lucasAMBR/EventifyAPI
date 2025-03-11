package com.equipeAcelera.EventifyAPI.DTOs.post.exhibition;

import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.like.exhibition.LikeDTO;

public class PostDTO {
    private int id;
    private String username;
    private String content;
    private List<String> imagesPaths;
    private List<LikeDTO> likeList;
    
    public PostDTO(int id, String username, String content, List<String> imagesPaths, List<LikeDTO> likeList) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.imagesPaths = imagesPaths;
        this.likeList = likeList;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public List<String> getImagesPaths() {
        return imagesPaths;
    }
    public void setImagesPaths(List<String> imagesPaths) {
        this.imagesPaths = imagesPaths;
    }
    public List<LikeDTO> getLikeList() {
        return likeList;
    }
    public void setLikeList(List<LikeDTO> likeList) {
        this.likeList = likeList;
    }
    
    
}
