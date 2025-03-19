package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.post.ReducedPostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.NormalUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.OrganizerUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.ReducedUserDTO;
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
import com.equipeAcelera.EventifyAPI.utils.ValidationUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class UserService {

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

        NormalUser newUser = new NormalUser(
            userList.size() + 1, 
            user.getName(),
            formatedCPF, 
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

        //! Inserir aqui a função para enviar o email de boas vindas

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

        OrganizerUser newOrganizer = new OrganizerUser(
            userList.size() + 1, 
            user.getName(), 
            formatedCPF, 
            user.getEmail(), 
            CryptoUtils.encryptPassword(user.getPassword()), 
            ImageUtils.saveProfilePic(user.getProfilePic()), 
            new ArrayList<>(), 
            user.getContact(),
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>(), 
            new ArrayList<>()
        );
        
        userList.add(newOrganizer);

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

    // Acha um usuario pelo Id
    public User findUserById(int id){
        for(User user : userList){
            if(user.getId() == id){
                return user;
            }
        }
        throw new DataNotFoundException("User not found, invalid id!");
    }

    public UserDTO findUserByIdExhibition(int id){
        for(User user : userList){
            if(user.getId() == id){
                if(user instanceof NormalUser){
                    List<ReducedPostDTO> reducedPostList = user.getPostList().stream().map(post -> {
                        ReducedPostDTO reducedPost = new ReducedPostDTO(
                            post.getId(), 
                            post.getUserId(), 
                            post.getUserName(), 
                            post.getLikeList().size()
                        );
                    
                    return reducedPost;
                    }).collect(Collectors.toList());

                    List<ReducedUserDTO> reducedFollowingList = user.getFollowing().stream().map(userItem -> {
                        String type;
                        if(userItem instanceof NormalUser){
                            type = "NORMAL";
                        }else{
                            type = "ORGANIZER";
                        }
                        ReducedUserDTO reducedUser = new ReducedUserDTO(
                            userItem.getId(), 
                            userItem.getProfilePicPath(), 
                            userItem.getName(), 
                            type, 
                            userItem.getFollowers().size(), 
                            userItem.getFollowing().size(), 
                            userItem.getPostList().size()
                        );

                        return reducedUser;
                    }).collect(Collectors.toList());

                    List<ReducedUserDTO> reducedFollowerList = user.getFollowers().stream().map(userItem -> {
                        String type;
                        if(user instanceof NormalUser){
                            type = "NORMAL";
                        }else{
                            type = "ORGANIZER";
                        }
                        ReducedUserDTO reducedUser = new ReducedUserDTO(
                            user.getId(), 
                            user.getProfilePicPath(), 
                            user.getName(), 
                            type, 
                            user.getFollowers().size(), 
                            user.getFollowing().size(), 
                            user.getPostList().size()
                        );

                        return reducedUser;
                    }).collect(Collectors.toList());

                    NormalUserDTO userDTO = new NormalUserDTO(
                        id, 
                        user.getName(), 
                        user.getCpf(), 
                        user.getProfilePicPath(), 
                        reducedPostList, 
                        user.getLikeList(), 
                        reducedFollowingList, 
                        reducedFollowerList, 
                        ((NormalUser) user).getBirth(), 
                        ((NormalUser) user).getSubscriptions()
                    );

                    return userDTO;
                }
                if(user instanceof OrganizerUser){
                    List<ReducedPostDTO> reducedPostList = user.getPostList().stream().map(post -> {
                        ReducedPostDTO reducedPost = new ReducedPostDTO(
                            post.getId(), 
                            post.getUserId(), 
                            post.getUserName(), 
                            post.getLikeList().size()
                        );
                    
                    return reducedPost;
                    }).collect(Collectors.toList());

                    List<ReducedUserDTO> reducedFollowingList = user.getFollowing().stream().map(userItem -> {
                        String type;
                        if(userItem instanceof NormalUser){
                            type = "NORMAL";
                        }else{
                            type = "ORGANIZER";
                        }
                        ReducedUserDTO reducedUser = new ReducedUserDTO(
                            userItem.getId(), 
                            userItem.getProfilePicPath(), 
                            userItem.getName(), 
                            type, 
                            userItem.getFollowers().size(), 
                            userItem.getFollowing().size(), 
                            userItem.getPostList().size()
                        );

                        return reducedUser;
                    }).collect(Collectors.toList());

                    List<ReducedUserDTO> reducedFollowerList = user.getFollowers().stream().map(userItem -> {
                        String type;
                        if(userItem instanceof NormalUser){
                            type = "NORMAL";
                        }else{
                            type = "ORGANIZER";
                        }
                        ReducedUserDTO reducedUser = new ReducedUserDTO(
                            userItem.getId(), 
                            userItem.getProfilePicPath(), 
                            userItem.getName(), 
                            type, 
                            userItem.getFollowers().size(), 
                            userItem.getFollowing().size(), 
                            userItem.getPostList().size()
                        );

                        return reducedUser;
                    }).collect(Collectors.toList());

                    OrganizerUserDTO userDTO = new OrganizerUserDTO(
                        id, 
                        user.getName(), 
                        user.getCpf(), 
                        user.getProfilePicPath(), 
                        reducedPostList, 
                        user.getLikeList(), 
                        reducedFollowingList, 
                        reducedFollowerList, 
                        ((OrganizerUser) user).getContact(), 
                        ((OrganizerUser) user).getEventList()
                    );

                    return userDTO;
                }
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
