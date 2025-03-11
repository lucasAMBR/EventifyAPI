package com.equipeAcelera.EventifyAPI.DTOs.event.exhibition;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.subscription.exhibition.SubscriptionDTO;

public class EventDTO {
    protected int id;
    protected String title;
    protected String description;
    protected LocalDate date;
    protected LocalTime hour;
    protected String type;
    protected int guestLimit;
    protected List<SubscriptionDTO> subscription;
    protected String organizerName;
    protected String imagePath;

    public EventDTO(int id, String title, String description, LocalDate date, LocalTime hour, String type,
            int guestLimit, List<SubscriptionDTO> subscription, String organizerName, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.type = type;
        this.guestLimit = guestLimit;
        this.subscription = subscription;
        this.organizerName = organizerName;
        this.imagePath = imagePath;
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

    public List<SubscriptionDTO> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<SubscriptionDTO> subscription) {
        this.subscription = subscription;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    
}
