package com.equipeAcelera.EventifyAPI.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.post.CreateEventPostDTO;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Post.EventPost;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.AuthUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class EventPostService {
    
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    public Post createEventPost(CreateEventPostDTO postData){
        if(!AuthUtils.verifyExistentUserById(userService.viewUserList(), postData.getUserId())){
            throw new RuntimeException("User doesn't exist");
        }

        User findedUser = userService.findUserById(postData.getUserId());

        Event findedEvent = eventService.getEventById(postData.getEventId());

        EventPost newPost = new EventPost(
            PostService.postList.size() + 1, 
            postData.getUserId(),
            findedUser.getProfilePicPath(), 
            findedUser instanceof NormalUser ? "NORMAL" : "ORGANIZER",
            findedUser.getName(),
            postData.getContent(), 
            postData.getPostImages() == null ? new ArrayList<>() : ImageUtils.savePostPics(postData.getPostImages()), 
            new ArrayList<>(),
            new ArrayList<>(),
            findedEvent.getId(),
            LocalDateTime.now()
        );
                
        PostService.postList.add(newPost);

        findedUser.getPostList().add(newPost);

        return newPost;
    }

}
