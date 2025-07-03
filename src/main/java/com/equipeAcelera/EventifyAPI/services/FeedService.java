package com.equipeAcelera.EventifyAPI.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.user.ReducedUserDTO;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.User;

@Service
public class FeedService {
    
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    public List<Post> generatePopularFeed() {
        List<Post> userFeed = PostService.postList.stream()
            .sorted((post1, post2) -> Integer.compare(post2.getLikeList().size(), post1.getLikeList().size()))
            .limit(100)
            .collect(Collectors.toList());

        return userFeed;
    }

    public ReducedUserDTO getReducedUserById(int userId){
        User user = userService.findUserById(userId);

        ReducedUserDTO reducedUser = new ReducedUserDTO(
            user.getId(), 
            user.getProfilePicPath(), 
            user.getName(), 
            user instanceof NormalUser ? "DEAFULT" : "ORGANIZER", 
            user.getFollowers().size(), 
            user.getFollowing().size(), 
            user.getPostList().size()
        );

        return reducedUser;
    }

    public List<Event> getSuggestedPopularEvents(int userId){
        return eventService.getEventList().stream()
            .filter(event -> event.getSubscriptionList().stream().noneMatch(subs -> subs.getUserId() == userId))
            .filter(event -> event.getSubscriptionList().size() < event.getGuestLimit())
            .sorted((e1, e2) -> Integer.compare(e2.getSubscriptionList().size(), e1.getSubscriptionList().size()))
            .limit(3)
            .collect(Collectors.toList());
    }

    public List<ReducedUserDTO> generatePopularUsersBasedOnLoggedUser(int userId){
        User loggedUser = userService.findUserById(userId);

        List<User> followingList = loggedUser.getFollowing();

        List<User> notFollowing = userService.viewUserList().stream()
            .filter(user -> user.getId() != loggedUser.getId())
            .filter(user -> !followingList.contains(user))
            .sorted((u1, u2) -> Integer.compare(u2.getFollowers().size(), u1.getFollowers().size()))
            .limit(3)
            .collect(Collectors.toList());

        return notFollowing.stream().map(user -> {
            ReducedUserDTO reducedUser = new ReducedUserDTO(
                user.getId(), 
                user.getProfilePicPath(), 
                user.getName(), 
                user instanceof NormalUser ? "DEAFULT" : "ORGANIZER", 
                user.getFollowers().size(), 
                user.getFollowing().size(), 
                user.getPostList().size()
            );

            return reducedUser;
        }).toList();
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

    public List<ReducedUserDTO> memberListFromEvent(int eventId){
        Event event = eventService.getEventById(eventId);

        List<ReducedUserDTO> memberList = event.getSubscriptionList().stream()
            .map(subs -> {
                User user = userService.findUserById(subs.getUserId());

                return new ReducedUserDTO(
                    user.getId(), 
                    user.getProfilePicPath(), 
                    user.getName(), 
                    user instanceof NormalUser ? "DEAFULT" : "ORGANIZER", 
                    user.getFollowers().size(), 
                    user.getFollowing().size(), 
                    user.getPostList().size()
                );
            })
            .collect(Collectors.toList());

        return memberList;
    }

}
