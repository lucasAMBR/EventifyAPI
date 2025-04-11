package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.user.RegisterNormalUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.RegisterOrganizerUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.UserDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.InvalidArgumentException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UserAlreadyExistException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.AuthUtils;
import com.equipeAcelera.EventifyAPI.utils.CryptoUtils;
import com.equipeAcelera.EventifyAPI.utils.FormatationUtils;
import com.equipeAcelera.EventifyAPI.utils.ValidationUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private EmailService emailService;

    // Lista que armazena todos os usuarios do sistema, normais e organizadores
    public static List<User> userList = new ArrayList<>();

    // Cadastra usuario normal
    public NormalUser RegisterNormalUser(RegisterNormalUserDTO user){ 

        ValidationUtils.verifyEmail(user.getEmail());

        if(user.getPassword().length() < 6){
            throw new InvalidArgumentException("Invalid password, minimum: 6 chars");
        }
        
        String formatedCPF = ValidationUtils.verifyAndFormatCPF(user.getCpf());

        if(AuthUtils.verifyExistentUser(userList, user.getEmail())){
            throw new UserAlreadyExistException("This email is already in use!");
        }

        String profilePicPath;

        if(user.getProfilePic() == null){
            profilePicPath = "/uploads/profile_pic/default.png";
        }else{
            profilePicPath = ImageUtils.saveProfilePic(user.getProfilePic());
        }

        NormalUser newUser = new NormalUser(
            userList.size() + 1, 
            user.getName(),
            formatedCPF, 
            user.getEmail(), 
            CryptoUtils.encryptPassword(user.getPassword()), 
            profilePicPath,
            user.getBirth(),
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>()
        );

        userList.add(newUser);

        //Função para enviar o email de boas vindas para o usuário
        emailService.sendWelcomeToNormal(newUser.getEmail(), newUser.getName());

        return newUser;
    }

    //Cadastra um organizador
    public OrganizerUser RegisterOganizerUser(RegisterOrganizerUserDTO user){
        
        ValidationUtils.verifyEmail(user.getEmail());

        if(user.getPassword().length() < 6){
            throw new InvalidArgumentException("Invalid password, minimum: 6 chars");
        }
        
        String formatedCPF = ValidationUtils.verifyAndFormatCPF(user.getCpf());

        if(AuthUtils.verifyExistentUser(userList, user.getEmail())){
            throw new UserAlreadyExistException("This email is already in use!");
        }

        String profilePicPath;

        if(user.getProfilePic() == null){
            profilePicPath = "/uploads/profile_pic/default.png";
        }else{
            profilePicPath = ImageUtils.saveProfilePic(user.getProfilePic());
        }

        OrganizerUser newOrganizer = new OrganizerUser(
            userList.size() + 1, 
            user.getName(), 
            formatedCPF, 
            user.getEmail(), 
            CryptoUtils.encryptPassword(user.getPassword()), 
            profilePicPath, 
            new ArrayList<>(), 
            user.getContact(),
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>()
        );
        
        userList.add(newOrganizer);

        //Função para enviar o email de boas vindas para o organizador
        emailService.sendWelcomeToNormal(newOrganizer.getEmail(), newOrganizer.getName());

        return newOrganizer;
    }

    public void manageFollow(int actorId, int userId){
        
        User findedActor = findUserById(actorId);

        User findedUser = findUserById(userId);

        for(User item : findedActor.getFollowing()){
            if(item.getId() == findedUser.getId()){

                List<User> newFollowingList = findedActor.getFollowing()
                    .stream()
                    .filter(user -> user.getId() != item.getId())
                    .collect(Collectors.toList());

                List<User> newFollowerList = findedUser.getFollowers()
                    .stream()
                    .filter(user -> user.getId() != findedActor.getId())
                    .collect(Collectors.toList());
                
                findedActor.setFollowing(newFollowingList);
                findedUser.setFollowers(newFollowerList);

                return;
            }
        }

        findedActor.getFollowing().add(findedUser);
        findedUser.getFollowers().add(findedActor);

        return;
    }

    // Acha um usuario pelo Id PARA USO INTERNO
    public User findUserById(int id){
        for(User user : userList){
            if(user.getId() == id){
                return user;
            }
        }
        throw new DataNotFoundException("User not found, invalid id!");
    }

    // Acha um usuario pelo Id e formata para exibir
    public UserDTO findUserByIdExhibition(int id){
        for(User user : userList){
            if(user.getId() == id){
               UserDTO userDTO = FormatationUtils.formatUser(user);
               return userDTO;
            }
        }
        throw new DataNotFoundException("User not found, invalid id!");
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
