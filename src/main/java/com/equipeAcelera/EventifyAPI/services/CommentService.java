package com.equipeAcelera.EventifyAPI.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.comment.CreateCommentDTO;
import com.equipeAcelera.EventifyAPI.models.Comments.Comment;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.User.User;

@Service
public class CommentService {
    
    public static List<Comment> commentList = new ArrayList<>();

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    public Comment CreateComment(CreateCommentDTO commentData){
        
        User findedUser = userService.findUserById(commentData.getUserId());

        Post findedPost = postService.findPostById(commentData.getPostId());

        Comment newComment = new Comment(
            commentList.size() + 1, 
            findedUser.getId(), 
            findedUser.getName(), 
            findedPost.getId(), 
            commentData.getContent(), 
            LocalDateTime.now()
        );

        findedPost.getCommentList().add(newComment);
        commentList.add(newComment);

        return newComment;
    }

}
