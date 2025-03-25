package com.equipeAcelera.EventifyAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.user.UpdateUserDataDTO;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.models.User.User;

@Service
public class UpdateService {

        @Autowired
        UserService userService;

        @Autowired
        PostService postService;

        @Autowired 
        EventService eventService;

        @Autowired
        SubscriptionService subscriptionService;

        @Autowired 
        LikeService likeService;
    
        public void updateUserName(UpdateUserDataDTO userData){
        
        User findedUser = userService.findUserById(userData.getId());

        findedUser.setName(userData.getUserName());

        for(Post post : postService.listAllPosts()){
            if(post.getUserId() == findedUser.getId()){
                post.setUserName(userData.getUserName());
            }
        };

        for(Event event : eventService.getEventList()){
            if(event.getOrganizerId() == findedUser.getId()){
                event.setOrganizerName(userData.getUserName());;
            }
        }

        for(Like like : likeService.listAllLikes()){
            if(like.getUserId() == findedUser.getId()){
                like.setUserName(userData.getUserName());
            }
        }

        for(Subscription subs : subscriptionService.listAllSubs()){
            if(subs.getUserId() == findedUser.getId()){
                subs.setUserName(userData.getUserName());
            }
        }
    }


}
