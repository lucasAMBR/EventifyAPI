package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.post.CreatePostDTO;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.utils.AuthUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class PostService {
    
    @Autowired
    UserService userService;

    private static List<Post> postList = new ArrayList<>();

    //Cria uma postagem
    public Post CreateNewPost(CreatePostDTO postData){

        if(!AuthUtils.verifyExistentUserById(userService.viewUserList(), postData.getUserId())){
            throw new RuntimeException("User doesn't exist");
        }

        Post newPost = new Post(
            postList.size() + 1, 
            postData.getUserId(), 
            postData.getContent(), 
            ImageUtils.savePostPics(postData.getPostImages()), 
            new ArrayList<>()
        );
        
        return newPost;
    }
}
