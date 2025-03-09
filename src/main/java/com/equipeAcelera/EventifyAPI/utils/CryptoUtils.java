package com.equipeAcelera.EventifyAPI.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CryptoUtils {
    
    public static String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    } 

    public static boolean verifyPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }

}
