package com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedFunctionAccessException extends RuntimeException {
    public UnauthorizedFunctionAccessException(String message){
        super(message);
    }
}
