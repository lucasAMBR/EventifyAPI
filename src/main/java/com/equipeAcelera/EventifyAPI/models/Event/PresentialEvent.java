package com.equipeAcelera.EventifyAPI.models.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;

public class PresentialEvent extends Event {
    private String location;
    private double latitude;
    private double longitude;
    
    

    public PresentialEvent(int id, String title, String description, LocalDate date, LocalTime hour, String type,
            int guestLimit, int organizerId, List<Subscription> subscriptionList, String imagePath, boolean isActive,
            String location, double latitude, double longitude) {
        super(id, title, description, date, hour, type, guestLimit, organizerId, subscriptionList, imagePath, isActive);
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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

    
}
