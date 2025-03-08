package com.equipeAcelera.EventifyAPI.models.Subscription;

//! Status pode ser: AUSENTE ou PRESENTE (presente é quando o usuario confirmou a presença)

public class Subscription {
    private int id;
    private int userId;
    private int eventId;
    private String status;
    
    public Subscription(int id, int userId, int eventId, String status) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.status = status;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
