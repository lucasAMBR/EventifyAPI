package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.post.CreatePostDTO;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> CreatePost(CreatePostDTO postData){
        Post newPost = postService.CreateNewPost(postData);

        return ResponseEntity.ok().body(newPost);
    }

}
