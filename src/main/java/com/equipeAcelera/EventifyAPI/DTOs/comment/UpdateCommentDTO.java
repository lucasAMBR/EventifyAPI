package com.equipeAcelera.EventifyAPI.DTOs.comment;

public class UpdateCommentDTO {
    private int userId;
    private String content;

    public UpdateCommentDTO(int userId, String content) {
        this.content = content;
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
