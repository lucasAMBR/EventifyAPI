package com.equipeAcelera.EventifyAPI.DTOs.subscription;

public class GenerateSubscriptionDTO {
    private int userId;
    private int eventId;

    public GenerateSubscriptionDTO(int userId, int eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    
}
