package com.equipeAcelera.EventifyAPI.DTOs.auth;

public class LoginResponseDTO {
    public int id;
    public String role;

    public LoginResponseDTO(int id, String role) {
        this.id = id;
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
