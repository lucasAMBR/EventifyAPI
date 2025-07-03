package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.user.UpdateUserDataDTO;
import com.equipeAcelera.EventifyAPI.services.UpdateService;

@RestController
@RequestMapping("api/update")
public class UpdateController {
    
    @Autowired
    UpdateService updateService;

    // Atualiza Nome e/ou foto de perfil de um usuario
    @PutMapping("/userdata")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void UpdateUserName(@ModelAttribute UpdateUserDataDTO userData){
        updateService.updateUserData(userData);
    }

}
