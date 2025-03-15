package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.event.CreateOnlineEventDTO;
import com.equipeAcelera.EventifyAPI.DTOs.event.CreatePresentialEventDTO;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.services.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
    
    @Autowired
    EventService eventService;

    @PostMapping("/create/presential")
    public ResponseEntity<Event> CreatePresentialEvent(CreatePresentialEventDTO eventData){
        Event newEvent = eventService.createPresentialEvent(eventData);

        return ResponseEntity.ok().body(newEvent);
    }

    @PostMapping("/create/online")
    public ResponseEntity<Event> CreateOnlineEvent(CreateOnlineEventDTO eventData){
        Event newEvent = eventService.createOnlineEvent(eventData);

        return ResponseEntity.ok().body(newEvent);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Event> FindEventById(int id){
        Event findedEvent = eventService.getEventById(id);

        return ResponseEntity.ok().body(findedEvent);
    }
}
