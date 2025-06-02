package com.equipeAcelera.EventifyAPI.DTOs.ConfirmationCode;

public class GenerateConfirmationDTO {
    private String email;
    private String password;
    private double latitude;
    private double longitude;
    
    public GenerateConfirmationDTO(String email, String password, double latitude, double longitude) {
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
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
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
