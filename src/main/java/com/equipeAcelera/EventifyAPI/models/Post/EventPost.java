package com.equipeAcelera.EventifyAPI.models.Post;

import java.time.LocalDateTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Like.Like;

public class EventPost extends Post {
    private int eventId;

    public EventPost(int id, int userId, String userName, String content, List<String> imagesPath, List<Like> likeList, int eventId, LocalDateTime date) {
        super(id, userId, userName, content, imagesPath, likeList, date);
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    
}
