package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.post.CreateEventPostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.post.CreatePostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.post.UpdatePostDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.models.Post.EventPost;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.User.User;
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

        User findedUser = userService.findUserById(postData.getUserId());

        Post newPost = new Post(
            postList.size() + 1, 
            postData.getUserId(), 
            findedUser.getName(),
            postData.getContent(), 
            ImageUtils.savePostPics(postData.getPostImages()), 
            new ArrayList<>()
        );
                
        postList.add(newPost);

        findedUser.getPostList().add(newPost);

        return newPost;
    }

    public Post createEventPost(CreateEventPostDTO postData){
        if(!AuthUtils.verifyExistentUserById(userService.viewUserList(), postData.getUserId())){
            throw new RuntimeException("User doesn't exist");
        }

        User findedUser = userService.findUserById(postData.getUserId());

        EventPost newPost = new EventPost(
            postList.size() + 1, 
            postData.getUserId(), 
            findedUser.getName(),
            postData.getContent(), 
            ImageUtils.savePostPics(postData.getPostImages()), 
            new ArrayList<>(),
            postData.getEventId()
        );
                
        postList.add(newPost);

        findedUser.getPostList().add(newPost);

        return newPost;
    }

    public Post UpdatePostContent(UpdatePostDTO updateData){
        Post findedPost = findPostById(updateData.getPostId());

        findedPost.setContent(updateData.getContent());

        return findedPost;
    }

    // Acha um post pelo id
    public Post findPostById(int id){
        
        for(Post post : postList){
            if(post.getId() == id){
                return post;
            }
        }
        throw new DataNotFoundException("Invalid id, cannot find this post!");
    }

    // Lista todos os posts no sistema
    public List<Post> listAllPosts(){
        
        List<Post> finalPostList = postList.stream().map(post -> {
            return post;
        }).collect(Collectors.toList());

        return finalPostList;
    }
}
