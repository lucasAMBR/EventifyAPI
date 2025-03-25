package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.user.UpdateUserDataDTO;
import com.equipeAcelera.EventifyAPI.services.UpdateService;

@RestController
@RequestMapping("/update")
public class UpdateController {
    
    @Autowired
    UpdateService updateService;

    @PutMapping("/username")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateUserName(UpdateUserDataDTO userData){
        updateService.updateUserName(userData);
    }

}
