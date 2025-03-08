package com.equipeAcelera.EventifyAPI.models.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;

public class Event {
    protected int id;
    protected String title;
    protected String description;
    protected LocalDate date;
    protected LocalTime hour;
    protected String type;
    protected int guestLimit;
    protected List<Subscription> subscriptionList;
    protected String imagePath;
    protected boolean isActive;
    
    public Event(int id, String title, String description, LocalDate date, LocalTime hour, String type, int guestLimit,
            List<Subscription> subscriptionList, String imagePath, boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.type = type;
        this.guestLimit = guestLimit;
        this.subscriptionList = subscriptionList;
        this.imagePath = imagePath;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGuestLimit() {
        return guestLimit;
    }

    public void setGuestLimit(int guestLimit) {
        this.guestLimit = guestLimit;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }  

    
}
