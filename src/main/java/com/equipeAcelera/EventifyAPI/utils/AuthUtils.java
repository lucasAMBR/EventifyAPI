package com.equipeAcelera.EventifyAPI.utils;

import java.util.List;

import com.equipeAcelera.EventifyAPI.models.User.User;

public class AuthUtils {
    public static boolean verifyExistentUser(List<User> userList, String email){
        for(User user : userList){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public static boolean verifyExistentUserById(List<User> userList, int id){
        for(User user : userList){
            if(user.getId() == id){
                return true;
            }
        }
        return false;
    }
}
