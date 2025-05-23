package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.auth.LoginResponseDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.LoginNormalUserDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.models.LoginHistory.LoginHistory;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.CryptoUtils;

@Service
public class AuthService {

    // lista com todas as instancias de item de historico de login
    private static List<LoginHistory> loginHistoryList = new ArrayList<>();

    // Realiza o login, retorna User e adiciona historico
    public LoginResponseDTO loginNormalUser(LoginNormalUserDTO userCredentials, String ip){
        
        for(User user : UserService.userList){
            if(user.getEmail().equals(userCredentials.getEmail())){
                if(CryptoUtils.verifyPassword(userCredentials.getPassword(), user.getPassword())){

                    LoginHistory loginHistory = new LoginHistory(loginHistoryList.size() + 1, user.getId(), ip);

                    loginHistoryList.add(loginHistory);

                    if(user instanceof NormalUser){
                        return new LoginResponseDTO(user.getId(), "DEFAULT");
                    }else{
                        return new LoginResponseDTO(user.getId(), "ORGANIZER");
                    }
                }
            }
        }
        throw new DataNotFoundException("User not found!");
    }

    // Retorna historico de um usuario
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
