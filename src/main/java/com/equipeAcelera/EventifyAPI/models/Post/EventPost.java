package com.equipeAcelera.EventifyAPI.models.Post;

import java.time.LocalDateTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Comments.Comment;
import com.equipeAcelera.EventifyAPI.models.Like.Like;

public class EventPost extends Post {
    private int eventId;

    public EventPost(int id, int userId, String userProfilePic, String userType, String userName, String content, List<String> imagesPath, List<Like> likeList, List<Comment> commentList, int eventId, LocalDateTime date) {
        super(id, userId, userProfilePic, userType, userName, content, imagesPath, likeList, commentList, date);
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    
}
