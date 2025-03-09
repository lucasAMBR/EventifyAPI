package com.equipeAcelera.EventifyAPI.models.LoginHistory;

import java.time.LocalDateTime;

public class LoginHistory {
    private int id;
    private int userID;
    private LocalDateTime date;
    private String deviceIp;

    public LoginHistory(int id, int userID, String deviceIp) {
        this.id = id;
        this.userID = userID;
        this.date = LocalDateTime.now();
        this.deviceIp = deviceIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    
}
