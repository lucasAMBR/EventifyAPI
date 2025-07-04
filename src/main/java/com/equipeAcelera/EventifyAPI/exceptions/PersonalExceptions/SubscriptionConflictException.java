package com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SubscriptionConflictException extends RuntimeException{
    public SubscriptionConflictException(String message){
        super(message);
    }
}
