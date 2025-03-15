package com.equipeAcelera.EventifyAPI.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.services.LikeService;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    
    @Autowired
    LikeService likeService;

    //PostMapping
    //public ResponseEntity<String> like(@RequestBody  int userId, @RequestBody int postId){
        //likeService.addLike(userId, postId);

        //return ResponseEntity.ok().body("LIKE");
    //}

}
