package com.equipeAcelera.EventifyAPI.DTOs.like.exhibition;

public class LikeDTO {
    private int id;
    private int postId;
    private String username;
    
    public LikeDTO(int id, int postId, String username) {
        this.id = id;
        this.postId = postId;
        this.username = username;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    
}
