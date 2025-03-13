package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Cria uma postagem
    @PostMapping("/create")
    public ResponseEntity<Post> CreatePost(CreatePostDTO postData){
        Post newPost = postService.CreateNewPost(postData);

        return ResponseEntity.ok().body(newPost);
    }

    // Acha um post pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Post> FindPostById(@PathVariable int id){
        Post findedPost = postService.findPostById(id);

        return ResponseEntity.ok().body(findedPost);
    }

    // Acha todos os posts do sistema
    @GetMapping("/list")
    public ResponseEntity<List<Post>> listAllPosts(){
        List<Post> postList = postService.listAllPosts();

        return ResponseEntity.ok().body(postList);
    }

}
