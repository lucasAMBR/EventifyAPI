package com.equipeAcelera.EventifyAPI.DTOs.comment;

public class UpdateCommentDTO {
    private String content;

    public UpdateCommentDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
