package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.event.CreatePresentialEventDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAccessException;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Event.PresentialEvent;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.GeocodingUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class EventService {
    private static List<Event> eventList = new ArrayList<>();

    @Autowired
    UserService userService;


    // O ENDEREÇO DEVE SER ENVIADO DA SEGUINTE MANEIRA: 
    // RUA, NUMERO, CIDADE, SIGLA DO ESTADO
    // EXEMPLO: Avenida Paulista, 1000, São Paulo, SP
    
    public Event createPresentialEvent(CreatePresentialEventDTO eventData){

        User eventHost = userService.findUserById(eventData.getOrganizerId());

        Map<String, Double> latitudeAndLongitude = GeocodingUtils.getLatitudeLongitude(eventData.getLocation());

        PresentialEvent newEvent = new PresentialEvent(
        eventList.size() + 1,
        eventData.getTitle(), 
        eventData.getDescription(),
        eventData.getDate(), 
        eventData.getHour(), 
        "PRESENTIAL", 
        eventData.getGuestLimit(), 
        eventData.getOrganizerId(), 
        eventHost.getName(), 
        new ArrayList<>(), 
        ImageUtils.saveEventBannerPic(eventData.getImage()), 
        true,
        eventData.getLocation(),
        latitudeAndLongitude.get("latitude"),
        latitudeAndLongitude.get("longitude")
        );

        if(eventHost instanceof OrganizerUser){
            ((OrganizerUser) eventHost).getEventList().add(newEvent);
            eventList.add(newEvent);
            return newEvent;
        }

        throw new UnauthorizedFunctionAccessException("Only Organizers can create a event!");
    }

    // Pega um evento pelo id
    public Event getEventById(int id){
        for(Event event : eventList){
            if(event.getId() == id){
                return event;
            }
        }

        throw new DataNotFoundException("Evento não encontrado!");
    }

    //Lista todos os eventos no sistema
    public List<Event> getEventList(){
        List<Event> allEventsList = new ArrayList<>();

        for(Event event : eventList){
            allEventsList.add(event);
        }

        return allEventsList;
    } 
}
