package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.user.ReducedUserDTO;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.services.FeedService;

@RestController
@RequestMapping("/api/feed")
public class FeedController {
    
    @Autowired
    FeedService feedService;

    @GetMapping("/popular/{userId}")
    public ResponseEntity<List<Post>> GetUserPopularFeed(@PathVariable int userId){
        List<Post> userFeed = feedService.generatePopularFeed();

        return ResponseEntity.ok().body(userFeed);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<Post>> getUserFollowingFeed(@PathVariable int userId){
        List<Post> userFeed = feedService.generateFollowingFeed(userId);
        
        return ResponseEntity.ok().body(userFeed);
    }

    @GetMapping("/popular-users/{userId}")
    public ResponseEntity<List<ReducedUserDTO>> generatePopularUsers(@PathVariable int userId){
        return ResponseEntity.ok().body(feedService.generatePopularUsersBasedOnLoggedUser(userId));
    }

    @GetMapping("/popular/events/{userId}")
    public ResponseEntity<List<Event>> getRecomendedEventsByUserId(@PathVariable int userId){
        return ResponseEntity.ok().body(feedService.getSuggestedPopularEvents(userId));
    }

    @GetMapping("/members/{eventId}")
    public ResponseEntity<List<ReducedUserDTO>> getMembersFromEvent(@PathVariable int eventId){
        return ResponseEntity.ok().body(feedService.memberListFromEvent(eventId));
    }

}
