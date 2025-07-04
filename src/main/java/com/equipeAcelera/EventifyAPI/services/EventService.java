package com.equipeAcelera.EventifyAPI.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.event.CreateOnlineEventDTO;
import com.equipeAcelera.EventifyAPI.DTOs.event.CreatePresentialEventDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAccessException;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Event.OnlineEvent;
import com.equipeAcelera.EventifyAPI.models.Event.PresentialEvent;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.GeocodingUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class EventService {
    public static List<Event> eventList = new ArrayList<>();

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

    public OnlineEvent createOnlineEvent(CreateOnlineEventDTO eventData){
        
        User findedUser = userService.findUserById(eventData.getOrganizerId());
        if(findedUser instanceof NormalUser){
            throw new UnauthorizedFunctionAccessException("You cannot create a event, only organizers can do it!");
        }

        OnlineEvent newEvent = new OnlineEvent(
            eventList.size() + 1, 
            eventData.getTitle(), 
            eventData.getDescription(), 
            eventData.getDate(), 
            eventData.getHour(), 
            "ONLINE", 
            eventData.getGuestLimit(), 
            eventData.getOrganizerId(), 
            findedUser.getName(), 
            new ArrayList<>(), 
            ImageUtils.saveEventBannerPic(eventData.getImage()),
            true, 
            eventData.getEventLink()
        );

        ((OrganizerUser) findedUser).getEventList().add(newEvent);

        eventList.add(newEvent);

        return newEvent;
    }

    // Cancela um evento e avisa os inscritos
    public void cancelEvent(int eventId){
    Event findedEvent = getEventById(eventId);
    List<Subscription> subsList = findedEvent.getSubscriptionList();

    for (Subscription sub : subsList) {
        userService.removeSubscriptionFromUser(sub.getUserId(), sub.getEventId());
    }

    findedEvent.setSubscriptionList(new ArrayList<>());
    findedEvent.setActive(false);
    }

    // Lista todos os eventos de um usuario
    public List<Event> getAllEventFromUserById(int userId){
        List<Event> userEventList = eventList.stream()
            .filter(event -> event.getOrganizerId() == userId)
            .collect(Collectors.toList());
        
        return userEventList;
    }

    public List<Event> getPopularEvents(){
        List<Event> popularEvents = eventList.stream()
            .filter(event -> !event.getDate().isBefore(LocalDate.now()) && event.isActive())
            .sorted((event1, event2) -> Integer.compare(event2.getSubscriptionList().size(), event1.getSubscriptionList().size()))
            .limit(4)
            .collect(Collectors.toList());
        
        return popularEvents;
    }

    public List<Event> getOrdenedRecentEvents(){
        List<Event> recentEvents = eventList.stream()
            .filter(event -> event.isActive())
            .sorted((event1, event2) -> Integer.compare(event2.getId(), event1.getId()))
            .limit(20)
            .collect(Collectors.toList());
        
        return recentEvents;
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
    public List<Event> getEventList() {
        return eventList.stream()
        .filter(event -> !event.getDate().isBefore(LocalDate.now())) // só eventos futuros ou de hoje
        .sorted(Comparator.comparing(Event::getDate))
        .collect(Collectors.toList());
}
}
