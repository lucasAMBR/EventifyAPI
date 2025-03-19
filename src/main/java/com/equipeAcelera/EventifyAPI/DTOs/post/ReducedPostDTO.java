package com.equipeAcelera.EventifyAPI.DTOs.post;

public class ReducedPostDTO {
    protected int id;
    protected int userId;
    protected String userName;
    protected int likes;
    
    public ReducedPostDTO(int id, int userId, String userName, int likes) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.likes = likes;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    
}
