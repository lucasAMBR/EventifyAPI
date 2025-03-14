package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.subscription.GenerateSubscriptionDTO;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.services.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    
    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<Subscription> GenerateSubscription(GenerateSubscriptionDTO subs){
        Subscription newSub = subscriptionService.generateSubscription(subs);
        
        return ResponseEntity.ok().body(newSub);
    }

}
