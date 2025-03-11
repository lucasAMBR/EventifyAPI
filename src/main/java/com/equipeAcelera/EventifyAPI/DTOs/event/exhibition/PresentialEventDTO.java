package com.equipeAcelera.EventifyAPI.DTOs.event.exhibition;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.subscription.exhibition.SubscriptionDTO;

public class PresentialEventDTO extends EventDTO {
    private double latitude;
    private double longitude;
    private String location;
    
    public PresentialEventDTO(int id, String title, String description, LocalDate date, LocalTime hour, String type,
            int guestLimit, List<SubscriptionDTO> subscription, String organizerName, String imagePath, double latitude,
            double longitude, String location) {
        super(id, title, description, date, hour, type, guestLimit, subscription, organizerName, imagePath);
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
}
