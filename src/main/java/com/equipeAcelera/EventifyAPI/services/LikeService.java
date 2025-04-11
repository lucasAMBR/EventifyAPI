package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.User.User;

@Service
public class LikeService {
    private List<Like> likeList = new ArrayList<>();  
    
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    public Like addLike(int userId, int postId){

        User findedUser = userService.findUserById(userId);

        Post findedPost = postService.findPostById(postId);

        for(Like like : likeList){         
            if(like.getUserId() == userId && like.getPostId() == postId){

            
                List<Like> newUserLikeList = findedUser.getLikeList()
                .stream()
                .filter(likeItem -> likeItem.getPostId() != like.getPostId())
                .collect(Collectors.toList());

                findedUser.setLikeList(newUserLikeList);

                List<Like> newPostLikeList = findedPost.getLikeList().stream().filter(likeItem -> likeItem.getPostId() != like.getPostId()).collect(Collectors.toList());

                findedPost.setLikeList(newPostLikeList);

                likeList.remove(like);

                return like;
            }
        }

        Like newLike = new Like(
            likeList.size() + 1, 
            userId, 
            findedUser.getName(),
            postId
        );

        findedUser.getLikeList().add(newLike);

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
 
    public List<Like> listAllLikes(){
        List<Like> newLikeList = likeList.stream().map(like -> {
            return like;
        }).collect(Collectors.toList());

        return newLikeList;
    }

}
