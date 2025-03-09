package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.user.LoginNormalUserDTO;
import com.equipeAcelera.EventifyAPI.models.LoginHistory.LoginHistory;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.CryptoUtils;

@Service
public class AuthService {

    private static List<LoginHistory> loginHistoryList = new ArrayList<>();

    public User loginNormalUser(LoginNormalUserDTO userCredentials, String ip){
        
        for(User user : UserService.userList){
            if(user.getEmail().equals(userCredentials.getEmail())){
                if(CryptoUtils.verifyPassword(userCredentials.getPassword(), user.getPassword())){

                    LoginHistory loginHistory = new LoginHistory(loginHistoryList.size() + 1, user.getId(), ip);

                    loginHistoryList.add(loginHistory);

                    return user;
                }
            }
        }
        throw new RuntimeException("Usuario nao encontrado");
    }

    public List<LoginHistory> getLoginHistorybyUserId(int id){
        List<LoginHistory> history = new ArrayList<>();

        for(LoginHistory item : loginHistoryList){
            if(item.getUserID() == id){
                history.add(item);
            }    
        }

        return history;
    }
}
