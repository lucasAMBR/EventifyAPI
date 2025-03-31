package com.equipeAcelera.EventifyAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.post.CreateEventPostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.post.CreatePostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.post.UpdatePostDTO;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.services.PostLikeService;
import com.equipeAcelera.EventifyAPI.services.PostService;
import com.equipeAcelera.EventifyAPI.services.UpdateService;

@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Autowired
    PostService postService;

    @Autowired
    UpdateService updateService;

    @Autowired
    PostLikeService postLikeService;

    // Cria uma postagem
    @PostMapping("/create")
    public ResponseEntity<Post> CreatePost(CreatePostDTO postData){
        Post newPost = postService.CreateNewPost(postData);

        return ResponseEntity.ok().body(newPost);
    }

    @PostMapping("/create/event")
    public ResponseEntity<Post> createEventPost(CreateEventPostDTO postData){
        Post newEventPost = postService.createEventPost(postData);

        return ResponseEntity.ok().body(newEventPost);
    }

    // Acha um post pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Post> FindPostById(@PathVariable int id){
        Post findedPost = postService.findPostById(id);

        return ResponseEntity.ok().body(findedPost);
    }

    // Atualiza os dados de um post
    @PutMapping("/update")
    public ResponseEntity<Post> UpdatePostContent(UpdatePostDTO updateData){
        Post findedPost = updateService.UpdatePostContent(updateData);

        return ResponseEntity.ok().body(findedPost);
    }

    // Lista todos os posts curtidos por um usuario
    @GetMapping("/liked/user/{userId}")
    public ResponseEntity<List<Post>> listAllLikedPostByUser(@PathVariable int userId){
        List<Post> likedList = postLikeService.listAllLikedPostByUserId(userId);

        return ResponseEntity.ok().body(likedList);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Post>> listAllEventPostsByEventId(@PathVariable int eventId){
        List<Post> postList = postLikeService.listEventPosts(eventId);

        return ResponseEntity.ok().body(postList);
    }

    // Acha todos os posts do sistema
    @GetMapping("/list")
    public ResponseEntity<List<Post>> listAllPosts(){
        List<Post> postList = postService.listAllPosts();

        return ResponseEntity.ok().body(postList);
    }

}
