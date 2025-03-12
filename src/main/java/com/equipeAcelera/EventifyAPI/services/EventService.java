package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.models.Event.Event;

@Service
public class EventService {
    private static List<Event> eventList = new ArrayList<>();

    public List<Event> getEventList(){
        List<Event> allEventsList = new ArrayList<>();

        for(Event event : eventList){
            allEventsList.add(event);
        }

        return allEventsList;
    } 
}
