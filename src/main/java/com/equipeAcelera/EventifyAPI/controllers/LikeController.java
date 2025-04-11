package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.services.LikeService;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    
    @Autowired
    LikeService likeService;

    // Adiciona ou remove o like de uma postagem
    @PostMapping("/add/{userId}/{postId}")
    public ResponseEntity<Like> like(@PathVariable int userId, @PathVariable int postId){
        Like like = likeService.addLike(userId, postId);

        return ResponseEntity.ok().body(like);
    }

}
