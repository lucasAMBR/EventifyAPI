package com.equipeAcelera.EventifyAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.InvalidArgumentException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAcessException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UserAlreadyExistException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

    //Caso tente inserir uma informação invalida
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPassword(InvalidArgumentException ex){
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(), 
            HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Caso tente cadastrar alguem ja cadastrado
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExist(UserAlreadyExistException ex){
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Caso Usuario nao seja encontrado
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(DataNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Caso tente acessar um funcao nao permitida para o tipo de usuario
    @ExceptionHandler(UnauthorizedFunctionAcessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedFunctionAcess(UnauthorizedFunctionAcessException ex){
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.UNAUTHORIZED.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Trata erros genericos (nao tratados)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(), 
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
