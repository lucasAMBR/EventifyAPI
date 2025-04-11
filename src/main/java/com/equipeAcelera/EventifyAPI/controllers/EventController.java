package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.event.CreateOnlineEventDTO;
import com.equipeAcelera.EventifyAPI.DTOs.event.CreatePresentialEventDTO;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.services.EventService;
import com.equipeAcelera.EventifyAPI.services.UpdateService;

@RestController
@RequestMapping("/api/event")
public class EventController {
    
    @Autowired
    EventService eventService;

    @Autowired
    UpdateService updateService;

    // Cria Eventos Presenciais
    @PostMapping("/create/presential")
    public ResponseEntity<Event> CreatePresentialEvent(@ModelAttribute CreatePresentialEventDTO eventData){
        Event newEvent = eventService.createPresentialEvent(eventData);

        return ResponseEntity.ok().body(newEvent);
    }

    // Cria Eventos online
    @PostMapping("/create/online")
    public ResponseEntity<Event> CreateOnlineEvent(@ModelAttribute CreateOnlineEventDTO eventData){
        Event newEvent = eventService.createOnlineEvent(eventData);

        return ResponseEntity.ok().body(newEvent);
    }

    @PutMapping("/cancel/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  CancelEvent(@PathVariable int eventId){
        eventService.cancelEvent(eventId);
    }

    @DeleteMapping("/canceled/delete/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void RemoveCanceledEvent(@PathVariable int eventId){
        updateService.removeCanceledEvent(eventId);
    }

    // Lista todos os eventos de um organizador
    @GetMapping("/list/user/{userId}")
    public ResponseEntity<List<Event>> ListAllUserEvents(@PathVariable int userId){
        List<Event> userEventList = eventService.getAllEventFromUserById(userId);

        return ResponseEntity.ok().body(userEventList);
    }

    // Acha um evento pelo Id
    @GetMapping("/find/{id}")
    public ResponseEntity<Event> FindEventById(int id){
        Event findedEvent = eventService.getEventById(id);

        return ResponseEntity.ok().body(findedEvent);
    }
}
