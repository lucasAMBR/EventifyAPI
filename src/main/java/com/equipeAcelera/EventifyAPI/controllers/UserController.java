package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.user.RegisterNormalUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.RegisterOrganizerUserDTO;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    // Cria um user normal
    @PostMapping("/register-normal")
    public ResponseEntity<NormalUser> RegisterNormalUser(RegisterNormalUserDTO user){
        NormalUser newUser = userService.RegisterNormalUser(user);
        
        return ResponseEntity.ok().body(newUser);
    }

    // Cria um usuario organizador
    @PostMapping("/register-organizer")
    public ResponseEntity<OrganizerUser> RegisterOrganizerUser(RegisterOrganizerUserDTO user){
        OrganizerUser newUser = userService.RegisterOganizerUser(user);
        
        return ResponseEntity.ok().body(newUser);
    }

    // Lista todos os usuarios cadastrados (Apenas para fins de desenvolvimentp, remover quando tiver tudo pronto)
   @GetMapping("/lista")
    public ResponseEntity<List<User>> ListUsers(){
        List<User> userList = userService.viewUserList();

        return ResponseEntity.ok().body(userList);
    }
 
}
