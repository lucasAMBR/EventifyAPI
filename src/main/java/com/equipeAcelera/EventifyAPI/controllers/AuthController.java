package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.auth.EmailRequestDTO;
import com.equipeAcelera.EventifyAPI.DTOs.auth.ResetPasswordRequestDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.InvalidArgumentException;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.services.EmailService;
import com.equipeAcelera.EventifyAPI.services.PasswordResetService;
import com.equipeAcelera.EventifyAPI.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.auth.LoginResponseDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.LoginNormalUserDTO;
import com.equipeAcelera.EventifyAPI.models.LoginHistory.LoginHistory;
import com.equipeAcelera.EventifyAPI.services.AuthService;
import com.equipeAcelera.EventifyAPI.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;
    
    @Autowired
    AuthService authService;
    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private EmailService emailService;

    // Realiza o login, retorna um objeto User e captura o ip e adiciona um item no historico de login da conta
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> Login(@ModelAttribute LoginNormalUserDTO userCredentials, HttpServletRequest request){
        
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        
        LoginResponseDTO loginData = authService.loginNormalUser(userCredentials, clientIp);

        return ResponseEntity.ok().body(loginData);
    }

    // Puxa o historico de logins da conta pelo ID dela
    @GetMapping("/history/{id}")
    public ResponseEntity<List<LoginHistory>> getLoginHistoryByUserId(@PathVariable int id){
        List<LoginHistory> history = authService.getLoginHistorybyUserId(id);

        return ResponseEntity.ok().body(history);
    }

    // Endpoint para solicitar reset de senha
    @PostMapping("/request-reset-code")
    public ResponseEntity<Void> requestResetCode(@RequestBody EmailRequestDTO request) {
        User user = userService.findUserByEmail(request.getEmail());
        String token = passwordResetService.generateResetToken(request.getEmail()); // Envia email
        emailService.sendResetCodeEmail(user.getEmail(), user.getName(), token);
        return ResponseEntity.ok().build();
    }

    // Endpoint para resetar senha (token e email no body)
    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequestDTO request) {
        if (!passwordResetService.validateToken(request.getEmail(), request.getToken())) {
            throw new InvalidArgumentException("Código inválido ou expirado");
        }

        User user = userService.findUserByEmail(request.getEmail());
        user.setPassword(CryptoUtils.encryptPassword(request.getNewPassword()));
        passwordResetService.removeToken(request.getEmail());

        return ResponseEntity.ok().build();
    }

}
