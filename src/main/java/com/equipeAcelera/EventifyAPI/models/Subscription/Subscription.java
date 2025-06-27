package com.equipeAcelera.EventifyAPI.models.Subscription;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

//! Status pode ser: AUSENTE ou PRESENTE (presente é quando o usuario confirmou a presença)

public class Subscription {
    private int id;
    private int userId;
    private String userName;
    private int eventId;
    private String eventBannerPath;
    private String eventTitle;
    protected LocalDate date;
    protected LocalTime hour;
    private String status;
    
    public Subscription(int id, int userId, String userName, int eventId, String eventBannerPath, String eventTitle, LocalDate date, LocalTime hour, String status) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.eventId = eventId;
        this.eventBannerPath = eventBannerPath;
        this.eventTitle = eventTitle;
        this.date = date;
        this.hour = hour;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventBannerPath() {
        return eventBannerPath;
    }

    public void setEventBannerPath(String eventBannerPath) {
        this.eventBannerPath = eventBannerPath;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return this.getEventId() == that.getEventId() && this.getUserId() == that.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getUserId());
    }
}
