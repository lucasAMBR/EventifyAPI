package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.ConfirmationCode.ConfirmationCode;
import com.equipeAcelera.EventifyAPI.DTOs.ConfirmationCode.GenerateConfirmationDTO;
import com.equipeAcelera.EventifyAPI.DTOs.ConfirmationCode.GenerateOnlineConfirmationDTO;
import com.equipeAcelera.EventifyAPI.services.ConfirmationService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/confirmation")
public class ConfirmationController {

    @Autowired
    ConfirmationService confirmationService;
    
    @PostMapping("{userId}/generate/{eventId}")
    public ResponseEntity<ConfirmationCode> generateConfirmationCode(@PathVariable int userId, @PathVariable int eventId){
        ConfirmationCode confirmCode = confirmationService.generateConfirmationCode(eventId, userId);

        return ResponseEntity.ok().body(confirmCode);
    }

    @PutMapping("confirm/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ConfirmPresence(@PathVariable String code, @ModelAttribute GenerateConfirmationDTO confirmData) {
        boolean isConfirmed = confirmationService.useConfirmationCode(confirmData.getEmail(), confirmData.getPassword(), code, confirmData.getLatitude(), confirmData.getLongitude());

        if(!isConfirmed){
            throw new RuntimeException("Cannot confirm your presence");
        }
    }

    @PutMapping("confirm/online/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ConfirmPresenceOnline(@PathVariable String code, @ModelAttribute GenerateOnlineConfirmationDTO confirmData) {
        boolean isConfirmed = confirmationService.useOnlineConfirmationCode(confirmData.getEmail(), confirmData.getPassword(), code);

        if(!isConfirmed){
            throw new RuntimeException("Cannot confirm your presence");
        }
    }

}
