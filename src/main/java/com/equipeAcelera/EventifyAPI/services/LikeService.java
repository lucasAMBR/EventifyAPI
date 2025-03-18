package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.like.AddLikeDTO;
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

    public Like addLike(AddLikeDTO likeData){

        User findedUser = userService.findUserById(likeData.getUserId());

        Post findedPost = postService.findPostById(likeData.getPostId());

        for(Like like : likeList){         
            if(like.getUserId() == likeData.getUserId() && like.getPostId() == likeData.getPostId()){

                if(findedUser instanceof NormalUser){
                    List<Like> newUserLikeList = ((NormalUser) findedUser).getLikeList()
                    .stream()
                    .filter(likeItem -> likeItem.getPostId() != like.getPostId())
                    .collect(Collectors.toList());

                    ((NormalUser)findedUser).setLikeList(newUserLikeList);
                }

                if(findedUser instanceof OrganizerUser){
                    List<Like> newUserLikeList = ((OrganizerUser) findedUser).getLikeList()
                    .stream()
                    .filter(likeItem -> likeItem.getPostId() != like.getPostId())
                    .collect(Collectors.toList());
                    
                    ((OrganizerUser)findedUser).setLikeList(newUserLikeList);
                }

                List<Like> newPostLikeList = findedPost.getLikeList().stream().filter(likeItem -> likeItem.getPostId() != like.getPostId()).collect(Collectors.toList());

                findedPost.setLikeList(newPostLikeList);

                likeList.remove(like);

                return like;
            }
        }

        Like newLike = new Like(
            likeList.size() + 1, 
            likeData.getUserId(), 
            findedUser.getName(),
            likeData.getPostId()
        );

        if(findedUser instanceof NormalUser){
            ((NormalUser) findedUser).getLikeList().add(newLike);
        }

        if(findedUser instanceof OrganizerUser){
            ((OrganizerUser) findedUser).getLikeList().add(newLike);
        }

        findedPost.getLikeList().add(newLike);

        likeList.add(newLike);

        return newLike;
    }

    public Like FindLikeById(int id){
        for(Like like : likeList){
            if(like.getId() == id){
                return like;
            }
        }

        throw new DataNotFoundException("Like not found!");
    }
     
}
