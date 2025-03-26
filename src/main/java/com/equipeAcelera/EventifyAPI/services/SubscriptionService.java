package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.subscription.GenerateSubscriptionDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAccessException;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.User;

@Service
public class SubscriptionService { 
    
    public static List<Subscription> subscriptionList = new ArrayList<>();

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    public Subscription generateSubscription(GenerateSubscriptionDTO subs){
        
        User findedUser = userService.findUserById(subs.getUserId());

        Event findedEvent = eventService.getEventById(subs.getEventId());

        Subscription newSub = new Subscription(
            subscriptionList.size() + 1, 
            subs.getUserId(), 
            findedUser.getName(), 
            subs.getEventId(), 
            findedEvent.getTitle(), 
            "ABSENT"
        );

        if(findedEvent.getSubscriptionList().size() < findedEvent.getGuestLimit()){
            findedEvent.getSubscriptionList().add(newSub);
        }else{
            throw new UnauthorizedFunctionAccessException("Guest limit!");
        }

        if(findedUser instanceof NormalUser){
            ((NormalUser) findedUser).getSubscriptions().add(newSub);
        }else{
            throw new UnauthorizedFunctionAccessException("Access not allowed!");
        }
        
        subscriptionList.add(newSub);

        return newSub;
    }

    public List<Subscription> listAllSubs(){
        List<Subscription> newSubsList = subscriptionList.stream().map(subs -> {
            return subs;
        }).collect(Collectors.toList());

        return newSubsList;
    }

}
