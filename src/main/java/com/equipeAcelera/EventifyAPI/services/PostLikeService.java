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

    public List<Post> listEventPosts(int eventId) {
        return PostService.postList.stream()
        .filter(post -> post instanceof EventPost) // garante que é um post de evento
        .map(post -> (EventPost) post)
        .filter(eventPost -> eventPost.getEventId() == eventId)
        .sorted((a, b) -> b.getDate().compareTo(a.getDate())) // ordena do mais novo para o mais antigo
        .collect(Collectors.toList());
}


}
