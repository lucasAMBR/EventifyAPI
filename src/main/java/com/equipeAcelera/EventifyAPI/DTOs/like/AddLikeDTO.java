package com.equipeAcelera.EventifyAPI.DTOs.like;

public class AddLikeDTO {
    private int userId;
    private int postId;
    
    public AddLikeDTO(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    
    
}
