package com.equipeAcelera.EventifyAPI.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.comment.CreateCommentDTO;
import com.equipeAcelera.EventifyAPI.DTOs.comment.UpdateCommentDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.DataNotFoundException;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAccessException;
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
            findedUser.getProfilePicPath(), 
            findedUser.getName(), 
            findedPost.getId(), 
            commentData.getContent(), 
            LocalDateTime.now()
        );

        findedPost.getCommentList().add(newComment);
        commentList.add(newComment);

        return newComment;
    }

    public Comment FindCommentById(int id){
        for (Comment comment : commentList) {
            if(comment.getId() == id){
                return comment;
            }
        }

        throw new DataNotFoundException("Not found");
    }

    public List<Comment> findAllCommentsByPostId(int postId){
        List<Comment> foundCommentList = new ArrayList<>();

        for (Comment comment : commentList) {
            if(comment.getPostId() == postId){
                foundCommentList.add(comment);
            }
        }
        return foundCommentList.stream().sorted((c1, c2) -> c2.getDate().compareTo(c1.getDate())).collect(Collectors.toList());
    }

    public Comment UpdateComment(int id, UpdateCommentDTO newContent){
        Comment foundedComment = FindCommentById(id);

        if(foundedComment.getUserId() != newContent.getUserId()){
            throw new UnauthorizedFunctionAccessException("You cannot update this post");
        }

        foundedComment.setContent(newContent.getContent());

        return foundedComment;
    }

    public void deleteComment(int userId, int commentId){
        Comment foundedComment = FindCommentById(commentId);

        if(foundedComment.getUserId() != userId){
            throw new UnauthorizedFunctionAccessException("You cannot delete this post");
        }

        Post findedPost = postService.findPostById(foundedComment.getPostId());

        commentList.remove(foundedComment);
        findedPost.getCommentList().remove(foundedComment);
    }
}
