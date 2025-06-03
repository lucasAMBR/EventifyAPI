package com.equipeAcelera.EventifyAPI.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.models.Post.Post;

@Service
public class FeedService {
    
    @Autowired
    UserService userService;

public List<Post> generatePopularFeed() {
    List<Post> userFeed = PostService.postList.stream()
        .sorted((post1, post2) -> Integer.compare(post2.getLikeList().size(), post1.getLikeList().size()))
        .limit(100)
        .collect(Collectors.toList());

    return userFeed;
}


    public List<Post> generateFollowingFeed(int id){

        List<Integer> userFollowing = userService.findUserById(id).getFollowing().stream().map(user -> {
            return user.getId();
        }).collect(Collectors.toList());

        List<Post> postList = PostService.postList.stream()
            .filter(post -> userFollowing.contains(post.getUserId()))
            .sorted((post1, post2) -> post2.getDate().compareTo(post1.getDate()))
            .collect(Collectors.toList());

        return postList;
    }

}
