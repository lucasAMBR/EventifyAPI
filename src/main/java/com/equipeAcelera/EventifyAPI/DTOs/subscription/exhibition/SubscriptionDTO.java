package com.equipeAcelera.EventifyAPI.DTOs.subscription.exhibition;

public class SubscriptionDTO {
    private int id;
    private int eventId;
    private String eventName;
    private int userId;
    private String username;
    
    public SubscriptionDTO(int id, int eventId, String eventName, int userId, String username) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.userId = userId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
