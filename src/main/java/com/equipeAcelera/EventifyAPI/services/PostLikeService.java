package com.equipeAcelera.EventifyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.EventPost;
import com.equipeAcelera.EventifyAPI.models.Post.Post;

@Service
public class PostLikeService {

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @Autowired
    EventService eventService;

    public List<Post> listAllLikedPostByUserId(int userId){
        List<Like> userLikeList = likeService.listAllLikes().stream()
            .filter(like -> like.getUserId() == userId)
            .collect(Collectors.toList());
        
        List<Post> likedPosts = new ArrayList<>();

        for(Like like : userLikeList){
            Post post = postService.findPostById(like.getPostId());

            likedPosts.add(post);
        }

        return likedPosts;
    }

    public List<Post> listEventPosts(int eventId){
        List<Post> eventList = postService.listAllPosts();

        List<Post> eventPostList = new ArrayList<>();

        for(Post post : eventList){
            if(post instanceof EventPost){
                if(((EventPost) post).getEventId() == eventId){
                    eventPostList.add(post);
                }
            }
        }

        return eventPostList;
    }

}
