package com.equipeAcelera.EventifyAPI.DTOs.event.exhibition;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.subscription.exhibition.SubscriptionDTO;

public class OnlineEventDTO extends EventDTO{
    private String link;

    public OnlineEventDTO(int id, String title, String description, LocalDate date, LocalTime hour, String type,
            int guestLimit, List<SubscriptionDTO> subscription, String organizerName, String imagePath, String link) {
        super(id, title, description, date, hour, type, guestLimit, subscription, organizerName, imagePath);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
}
