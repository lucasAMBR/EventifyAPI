package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.services.SubscriptionService;
import com.equipeAcelera.EventifyAPI.services.UpdateService;
import com.equipeAcelera.EventifyAPI.services.UserService;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    
    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UpdateService updateService;

    @Autowired
    UserService userService;

    // Inscreve um usuario em um evento
    @PostMapping("/create/{eventId}/{userId}")
    public ResponseEntity<Subscription> GenerateSubscription(@PathVariable int userId, @PathVariable int eventId){
        Subscription newSub = subscriptionService.generateSubscription(userId, eventId);
        
        return ResponseEntity.ok().body(newSub);
    }

    // Remove a inscrição de um usuario no evento
    @DeleteMapping("/cancel/{userId}/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void CancelSubscription(@PathVariable int userId, @PathVariable int eventId){
        updateService.CancelSubscription(userId, eventId);
    }

    // Lista Todas as incrições de um usuario
    @GetMapping("/list/user/{userId}")
    public ResponseEntity<List<Subscription>> listUserSubscriptions(@PathVariable int userId){
        List<Subscription> subsList = userService.listUserSubscriptions(userId);

        return ResponseEntity.ok().body(subsList);
    }
}
