package com.equipeAcelera.EventifyAPI.DTOs.user;

public class LoginNormalUserDTO {
    private String email;
    private String password;
    
    public LoginNormalUserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
