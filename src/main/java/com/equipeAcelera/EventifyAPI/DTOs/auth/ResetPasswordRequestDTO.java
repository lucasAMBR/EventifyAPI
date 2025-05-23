package com.equipeAcelera.EventifyAPI.DTOs.auth;

public class ResetPasswordRequestDTO {
    private String email;
    private String newPassword;
    private String token;

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
