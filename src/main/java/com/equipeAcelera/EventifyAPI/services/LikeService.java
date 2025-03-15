package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;

@Service
public class LikeService {
    private List<Like> likeList = new ArrayList<>();  
    
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    /*
        public void addLike(int userId, int postId){

        User findedUser = userService.findUserById(userId);

        Post findedPost = postService.findPostById(postId);

        if(findedUser instanceof NormalUser){
            for(Like like : likeList){
                if(like.getUserId() == userId && like.getPostId() == postId){
                   ((NormalUser) findedUser).getLikeList().remove(FindLikeById(like.getId()));
                   findedPost.getLikeList().remove(FindLikeById(like.getId()));

                   likeList.remove(like);

                   return;
                }
            }
        }

        if(findedUser instanceof OrganizerUser){
            for(Like like : likeList){
                if(like.getUserId() == userId && like.getPostId() == postId){
                   ((NormalUser) findedUser).getLikeList().remove(FindLikeById(like.getId()));
                   findedPost.getLikeList().remove(FindLikeById(like.getId()));

                   likeList.remove(like);

                   return;
                }
            }
        }

        Like newLike = new Like(
            likeList.size() + 1, 
            userId, 
            findedUser.getName(), 
            postId    
        );

        if(findedUser instanceof NormalUser){
            ((NormalUser) findedUser).getLikeList().add(newLike);
        }

        if(findedUser instanceof OrganizerUser){
            ((NormalUser) findedUser).getLikeList().add(newLike);
        }

        findedPost.getLikeList().add(newLike);

        likeList.add(newLike);

        return;

    }

    public Like FindLikeById(int id){
        for(Like like : likeList){
            if(like.getId() == id){
                return like;
            }
        }

        throw new DataNotFoundException("Like not found!");
    }
     
    */
}
