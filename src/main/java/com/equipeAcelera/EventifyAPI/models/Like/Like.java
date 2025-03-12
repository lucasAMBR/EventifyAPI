package com.equipeAcelera.EventifyAPI.models.Like;

public class Like {
    private int id;
    private int userId;
    private String userName;
    private int postId;
    
    public Like(int id, int userId, String userName, int postId) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.postId = postId;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
