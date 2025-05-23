package com.equipeAcelera.EventifyAPI.DTOs.event;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.web.multipart.MultipartFile;

public class UpdatePresentialEventDTO {
    private int organizerId;
    private int eventId;
    private String newTitle;
    private String newDescription;
    private LocalDate newDate;
    private LocalTime newHour;
    private Integer newGuestLimit;
    private MultipartFile newBannerImage;
    private String newLocation;

    public UpdatePresentialEventDTO(int organizerId, int eventId, String newTitle, String newDescription, LocalDate newDate,
            LocalTime newHour, int newGuestLimit, MultipartFile newBannerImage, String newLocation) {
        this.organizerId = organizerId;
        this.eventId = eventId;
        this.newTitle = newTitle;
        this.newDescription = newDescription;
        this.newDate = newDate;
        this.newHour = newHour;
        this.newGuestLimit = newGuestLimit;
        this.newBannerImage = newBannerImage;
        this.newLocation = newLocation;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }

    public LocalTime getNewHour() {
        return newHour;
    }

    public void setNewHour(LocalTime newHour) {
        this.newHour = newHour;
    }

    public int getNewGuestLimit() {
        return newGuestLimit;
    }

    public void setNewGuestLimit(int newGuestLimit) {
        this.newGuestLimit = newGuestLimit;
    }

    public MultipartFile getNewBannerImage() {
        return newBannerImage;
    }

    public void setNewBannerImage(MultipartFile newBannerImage) {
        this.newBannerImage = newBannerImage;
    }

    public String getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    
}
