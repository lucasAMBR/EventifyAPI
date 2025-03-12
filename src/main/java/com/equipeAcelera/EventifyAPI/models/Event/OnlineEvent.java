package com.equipeAcelera.EventifyAPI.models.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;

public class OnlineEvent extends Event {
    private String link;

    public OnlineEvent(int id, String title, String description, LocalDate date, LocalTime hour, String type,
            int guestLimit, int organizerId, String organizerName, List<Subscription> subscriptionList, String imagePath, boolean isActive, String link) {
        super(id, title, description, date, hour, type, guestLimit, organizerId, organizerName, subscriptionList, imagePath, isActive);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
