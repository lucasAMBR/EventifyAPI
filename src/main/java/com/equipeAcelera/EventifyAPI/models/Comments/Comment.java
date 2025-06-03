package com.equipeAcelera.EventifyAPI.models.Comments;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int userId;
    private String userProfilePic;
    private String userName;
    private int postId;
    private String content;
    private LocalDateTime date;

    public Comment(int id, int userId, String userProfilePic, String username, int postId, String content, LocalDateTime date) {
        this.id = id;
        this.userId = userId;
        this.userProfilePic = userProfilePic;
        this.userName = username;
        this.postId = postId;
        this.content = content;
        this.date = date;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }
    
}
