package com.equipeAcelera.EventifyAPI.DTOs.ConfirmationCode;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConfirmationCode {
    private String code;
    private LocalDate expirationDate;
    private LocalTime expirationTime;

    public ConfirmationCode(String code, LocalDate expirationDate, LocalTime expirationTime) {
        this.code = code;
        this.expirationDate = expirationDate;
        this.expirationTime = expirationTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    
}
