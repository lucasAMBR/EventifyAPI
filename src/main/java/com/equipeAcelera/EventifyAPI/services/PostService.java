package com.equipeAcelera.EventifyAPI.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.post.CreatePostDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.AuthUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class PostService {
    
    @Autowired
    UserService userService;

    public static List<Post> postList = new ArrayList<>();

    //Cria uma postagem
    public Post CreateNewPost(CreatePostDTO postData){

        if(!AuthUtils.verifyExistentUserById(userService.viewUserList(), postData.getUserId())){
            throw new RuntimeException("User doesn't exist");
        }

        User findedUser = userService.findUserById(postData.getUserId());

        Post newPost = new Post(
            postList.size() + 1, 
            postData.getUserId(), 
            findedUser.getProfilePicPath(),
            findedUser instanceof NormalUser ? "NORMAL" : "ORGANIZER",
            findedUser.getName(),
            postData.getContent(), 
            postData.getPostImages() != null ? ImageUtils.savePostPics(postData.getPostImages()) : new ArrayList<>(), 
            new ArrayList<>(),
            new ArrayList<>(),
            LocalDateTime.now()
        );
                
        postList.add(newPost);

        findedUser.getPostList().add(newPost);

        return newPost;
    }

    // Lista todos os posts de um usuario
    public List<Post> listPostsByUserId(int userId){
        return postList.stream()
            .filter(post -> post.getUserId() == userId)
            .sorted(Comparator.comparing(Post::getDate).reversed())
            .collect(Collectors.toList());
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
    public List<Post> listAllPosts() {
        return postList.stream()
            .sorted(Comparator.comparing(Post::getDate).reversed())
            .collect(Collectors.toList());
}

}
