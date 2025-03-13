package com.equipeAcelera.EventifyAPI.utils;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.InvalidArgumentException;

import br.com.caelum.stella.validation.CPFValidator;

public class CPFUtils {
    
    public static final CPFValidator validator = new CPFValidator();

    public static String formatCPF(String cpf){
        String cleanedCPF = cpf.replaceAll("[^0-9]", "");
        
        if(cleanedCPF.length() != 11){
            throw new InvalidArgumentException("Invalid CPF!");
        }

        if(cleanedCPF.chars().allMatch(c -> c == cleanedCPF.charAt(0))){
            throw new InvalidArgumentException("Invalid CPF!");
        }

        return cleanedCPF.substring(0, 3) + "." + cleanedCPF.substring(3, 6) + "." + cleanedCPF.substring(6, 9) + "-" + cleanedCPF.substring(9);
    }

}
