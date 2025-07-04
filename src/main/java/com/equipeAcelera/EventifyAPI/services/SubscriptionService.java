package com.equipeAcelera.EventifyAPI.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.post.CreatePostDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.SubscriptionConflictException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAccessException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.InvalidArgumentException;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
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

    @Autowired
    private PostService postService;

    public Subscription generateSubscription(int userId, int eventId){
        
        User findedUser = userService.findUserById(userId);

        if(!(findedUser instanceof NormalUser)){
            throw new UnauthorizedFunctionAccessException("You cannot subscribe to an event, only normal users can do it!");
        }

        Event findedEvent = eventService.getEventById(eventId);

        for(Subscription sub : ((NormalUser) findedUser).getSubscriptions()){
            Event event = eventService.getEventById(sub.getEventId());
            if(event.getDate().isEqual(findedEvent.getDate())){
                long diff = Duration.between(event.getHour(), findedEvent.getHour()).toHours();
                if(diff < 2){
                    throw new SubscriptionConflictException("You already have an event at this time!");
                }
            }
        }

        Subscription newSub = new Subscription(
            subscriptionList.size() + 1, 
            userId, 
            findedUser.getName(), 
            eventId, 
            findedEvent.getImagePath(),
            findedEvent.getTitle(),
            findedEvent.getDate(), 
            findedEvent.getHour(),
            "ABSENT"
        );

        if(findedEvent.getSubscriptionList().size() < findedEvent.getGuestLimit()){

            findedEvent.getSubscriptionList().add(newSub);

            if(!findedEvent.isActive()){
                throw new InvalidArgumentException("Event is not active!");
            }

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

        String autoPostContent = findedUser.getName() + " just singed up for the event " + findedEvent.getTitle() + "! it's going to be amazing - and you can join too. Secure your spot";

        CreatePostDTO autoPost = new CreatePostDTO(userId, autoPostContent, new ArrayList<>());

        Post createdPost = postService.CreateNewPost(autoPost);

        createdPost.getImagesPath().add(findedEvent.getImagePath());

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

    public void removeSubscription(int userId, int eventId) {
        subscriptionList = subscriptionList.stream()
            .filter(sub -> !(sub.getUserId() == userId && sub.getEventId() == eventId))
            .collect(Collectors.toList());
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

    @Scheduled(fixedRate = 60 * 1000)
    public void cancelExpiredSubscriptions(){
        for(Subscription sub : subscriptionList){
            Event event = eventService.getEventById(sub.getEventId());

            if(event.getDate().isEqual(LocalDate.now().minusDays(1))){
                if(event.getHour().isBefore(LocalTime.now())){
                    if(sub.getStatus().equals("ABSENT")){
                        System.out.println("Removendo inscrições de membros ausentes do evento " + event.getTitle() + "!");
                        removeSubscription(sub.getUserId(), sub.getEventId());
                    }
                }
            }
        }

        System.out.println("Limpando inscrições de membros ausentes do expiradas!");
    }
}
