package com.equipeAcelera.EventifyAPI.utils;

import org.apache.commons.validator.routines.EmailValidator;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.InvalidArgumentException;

public class ValidationUtils {


    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    public static boolean verifyEmail(String email){
        boolean isValid = emailValidator.isValid(email);
        if(isValid){
            return isValid;
        }else{
            throw new InvalidArgumentException("Invalid Email");
        }
    }
    
    public static String verifyAndFormatCPF(String cpf){

        if(!validCPF(cpf)){
            throw new InvalidArgumentException("Invalid CPF!");
        }

        String cleanedCPF = cpf.replaceAll("[^0-9]", "");
        
        if(cleanedCPF.length() != 11){
            throw new InvalidArgumentException("Invalid CPF!");
        }

        if(cleanedCPF.chars().allMatch(c -> c == cleanedCPF.charAt(0))){
            throw new InvalidArgumentException("Invalid CPF!");
        }

        return cleanedCPF.substring(0, 3) + "." + cleanedCPF.substring(3, 6) + "." + cleanedCPF.substring(6, 9) + "-" + cleanedCPF.substring(9);
    }

    public static boolean validCPF(String cpf){
        
        if(cpf.length() < 11){
            throw new InvalidArgumentException("Invalid CPF!");
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = (sum % 11 < 2) ? 0 : 11 - (sum % 11);

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = (sum % 11 < 2) ? 0 : 11 - (sum % 11);

        return (cpf.charAt(9) - '0' == firstDigit) && (cpf.charAt(10) - '0' == secondDigit);
    }

}
