package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.user.RegisterNormalUserDTO;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.CryptoUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class UserService {

    // Lista que armazena todos os usuarios do sistema, normais e organizadores
    public static List<User> userList = new ArrayList<>();

    // Cadastra usuario normal
    public NormalUser RegisterNormalUser(RegisterNormalUserDTO user){

        NormalUser newUser = new NormalUser(
            userList.size() + 1, 
            user.getName(), 
            user.getEmail(), 
            CryptoUtils.encryptPassword(user.getPassword()), 
            ImageUtils.saveProfilePic(user.getProfilePic()),
            user.getBirth(),
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>()
        );

        userList.add(newUser);

        return newUser;
    }

    // Retorna lista de usuarios
    public List<User> viewUserList(){
        List<User> newList = new ArrayList<>();

        for(User user: userList){
            newList.add(user);
        }

        return newList;
    }

}
