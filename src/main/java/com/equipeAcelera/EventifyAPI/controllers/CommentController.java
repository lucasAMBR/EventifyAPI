package com.equipeAcelera.EventifyAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.equipeAcelera.EventifyAPI.DTOs.comment.CreateCommentDTO;
import com.equipeAcelera.EventifyAPI.DTOs.comment.UpdateCommentDTO;
import com.equipeAcelera.EventifyAPI.models.Comments.Comment;
import com.equipeAcelera.EventifyAPI.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@ModelAttribute CreateCommentDTO commentData){
        Comment newComment = commentService.CreateComment(commentData);

        return ResponseEntity.ok().body(newComment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> UpdateComment(@PathVariable int commentId, @ModelAttribute UpdateCommentDTO commentData){
        Comment comment = commentService.UpdateComment(commentId, commentData);

        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int commentId, @RequestBody int userId){
        commentService.deleteComment(userId, commentId);
    }
}
