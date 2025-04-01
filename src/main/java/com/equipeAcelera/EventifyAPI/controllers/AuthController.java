package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.user.LoginNormalUserDTO;
import com.equipeAcelera.EventifyAPI.models.LoginHistory.LoginHistory;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.services.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    AuthService authService;
    
    // Realiza o login, retorna um objeto User e captura o ip e adiciona um item no historico de login da conta
    @PostMapping("/login")
    public ResponseEntity<User> Login(@ModelAttribute LoginNormalUserDTO userCredentials, HttpServletRequest request){
        
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        
        User user = authService.loginNormalUser(userCredentials, clientIp);

        return ResponseEntity.ok().body(user);
    }

    // Puxa o historico de logins da conta pelo ID dela
    @GetMapping("/history/{id}")
    public ResponseEntity<List<LoginHistory>> getLoginHistoryByUserId(@PathVariable int id){
        List<LoginHistory> history = authService.getLoginHistorybyUserId(id);

        return ResponseEntity.ok().body(history);
    }
}
