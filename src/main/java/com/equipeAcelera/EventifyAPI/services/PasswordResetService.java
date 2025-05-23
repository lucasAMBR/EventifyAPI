package com.equipeAcelera.EventifyAPI.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class PasswordResetService {
    private final Map<String, String> resetTokens = new HashMap<>(); // Email -> Token

    public String generateResetToken(String email) {
        String token = String.format("%06d", new Random().nextInt(999999));
        resetTokens.put(email, token);
        return token;
    }

    public boolean validateToken(String email, String token) {
        String storedToken = resetTokens.get(email);
        return storedToken != null && storedToken.equals(token);
    }

    public void removeToken(String email) {
        resetTokens.remove(email);
    }
}