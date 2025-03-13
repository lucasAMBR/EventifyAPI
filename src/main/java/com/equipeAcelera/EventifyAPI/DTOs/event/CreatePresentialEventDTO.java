package com.equipeAcelera.EventifyAPI.DTOs.event;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.web.multipart.MultipartFile;

public class CreatePresentialEventDTO {
    protected String title;
    protected String description;
    protected LocalDate date;
    protected LocalTime hour;
    protected String type;
    protected int guestLimit;
    protected int organizerId;
    protected MultipartFile image;
    protected String location;
    
    public CreatePresentialEventDTO(String title, String description, LocalDate date, LocalTime hour, String type,
            int guestLimit, int organizerId, MultipartFile image, String location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.type = type;
        this.guestLimit = guestLimit;
        this.organizerId = organizerId;
        this.image = image;
        this.location = location;
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

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImagePath(MultipartFile image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
}
