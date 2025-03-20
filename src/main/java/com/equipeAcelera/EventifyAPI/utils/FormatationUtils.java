package com.equipeAcelera.EventifyAPI.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.equipeAcelera.EventifyAPI.DTOs.post.ReducedPostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.NormalUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.OrganizerUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.ReducedUserDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.UserDTO;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;

public class FormatationUtils {
    public static UserDTO formatUser(User rawUser){
        if(rawUser instanceof NormalUser){
            List<ReducedPostDTO> reducedPostList = rawUser.getPostList().stream().map(post -> {
                ReducedPostDTO reducedPost = new ReducedPostDTO(
                    post.getId(), 
                    post.getUserId(), 
                    post.getUserName(), 
                    post.getContent(),
                    post.getLikeList().size()
                );
                    
                return reducedPost;
                }).collect(Collectors.toList());

                List<ReducedUserDTO> reducedFollowingList = rawUser.getFollowing().stream().map(userItem -> {
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

                List<ReducedUserDTO> reducedFollowerList = rawUser.getFollowers().stream().map(userItem -> {
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

                NormalUserDTO userDTO = new NormalUserDTO(
                    rawUser.getId(), 
                    rawUser.getName(), 
                    rawUser.getCpf(), 
                    rawUser.getProfilePicPath(), 
                    reducedPostList, 
                    rawUser.getLikeList(), 
                    reducedFollowingList, 
                    reducedFollowerList, 
                    ((NormalUser) rawUser).getBirth(), 
                    ((NormalUser) rawUser).getSubscriptions()
                );

                return userDTO;
            }
            if(rawUser instanceof OrganizerUser){
                List<ReducedPostDTO> reducedPostList = rawUser.getPostList().stream().map(post -> {
                    ReducedPostDTO reducedPost = new ReducedPostDTO(
                        post.getId(), 
                        post.getUserId(), 
                        post.getUserName(), 
                        post.getContent(),
                        post.getLikeList().size()
                    );
                    
                    return reducedPost;
                }).collect(Collectors.toList());

                List<ReducedUserDTO> reducedFollowingList = rawUser.getFollowing().stream().map(userItem -> {
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

                List<ReducedUserDTO> reducedFollowerList = rawUser.getFollowers().stream().map(userItem -> {
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
                    rawUser.getId(), 
                    rawUser.getName(), 
                    rawUser.getCpf(), 
                    rawUser.getProfilePicPath(), 
                    reducedPostList, 
                    rawUser.getLikeList(), 
                    reducedFollowingList, 
                    reducedFollowerList, 
                    ((OrganizerUser) rawUser).getContact(), 
                    ((OrganizerUser) rawUser).getEventList()
                );

                return userDTO;
            }else{
                throw new RuntimeException("Error");
            }
    }
}
