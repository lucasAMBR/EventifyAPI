package com.equipeAcelera.EventifyAPI.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private EmailService emailService;

    public Subscription generateSubscription(int userId, int eventId){
        
        User findedUser = userService.findUserById(userId);

        Event findedEvent = eventService.getEventById(eventId);

        Subscription newSub = new Subscription(
            subscriptionList.size() + 1, 
            userId, 
            findedUser.getName(), 
            eventId, 
            findedEvent.getTitle(), 
            "ABSENT"
        );

        if(findedEvent.getSubscriptionList().size() < findedEvent.getGuestLimit()){

            findedEvent.getSubscriptionList().add(newSub);

            if(findedEvent.getSubscriptionList().size() == findedEvent.getGuestLimit()) {
                String participantsList = formatParticipantsList(findedEvent.getSubscriptionList());

                User organizer = userService.findUserById(findedEvent.getOrganizerId());
                emailService.sendEventFullNotification(
                        organizer.getEmail(),
                        organizer.getName(),
                        findedEvent.getTitle(),
                        findedEvent.getGuestLimit(),
                        participantsList
                );}
        }else{
            throw new UnauthorizedFunctionAccessException("Guest limit!");
        }

        if(findedUser instanceof NormalUser){
            ((NormalUser) findedUser).getSubscriptions().add(newSub);
        }else{
            throw new UnauthorizedFunctionAccessException("Access not allowed!");
        }
        
        subscriptionList.add(newSub);

        emailService.sendSubscriptionConfirmation(findedUser.getEmail(), findedUser.getName());

        return newSub;
    }

    public List<Subscription> listAllSubs(){
        List<Subscription> newSubsList = subscriptionList.stream().map(subs -> {
            return subs;
        }).collect(Collectors.toList());

        return newSubsList;
    }

    public List<Subscription> listSubscriptionsByUserId(int userId){
        List<Subscription> userSubscriptions = SubscriptionService.subscriptionList.stream()
            .filter(subs -> subs.getUserId() == userId)
            .collect(Collectors.toList());

        return userSubscriptions;
    }

    private String formatParticipantsList(List<Subscription> subscriptions) {
        StringBuilder sb = new StringBuilder("""
        <table style='border-collapse: collapse; width: 100%%; margin: 20px 0;'>
            <thead>
                <tr style='background-color: #f2f2f2;'>
                    <th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Nome</th>
                    <th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Email</th>
                    <th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Data de Inscrição</th>
                </tr>
            </thead>
            <tbody>
        """);

        for (Subscription sub : subscriptions) {
            User user = userService.findUserById(sub.getUserId());
            sb.append(String.format("""
                <tr>
                    <td style='padding: 12px; border: 1px solid #ddd;'>%s</td>
                    <td style='padding: 12px; border: 1px solid #ddd;'>%s</td>
                    <td style='padding: 12px; border: 1px solid #ddd;'>%s</td>
                </tr>
            """,
                    user.getName(),
                    user.getEmail(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            ));
        }

        sb.append("</tbody></table>");
        return sb.toString();
    }

}
