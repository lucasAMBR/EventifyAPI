package com.equipeAcelera.EventifyAPI.models.User;

import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;

public class OrganizerUser extends User{
    private String contact;
    private List<Post> postList;
    private List<Event> eventList;
    private List<Like> likeList;
    private List<User> following;
    private List<User> followers;

    
    public OrganizerUser(int id, String name, String cpf, String email, String password, String profilePicPath, String contact,
            List<Post> postList, List<Event> eventList, List<Like> likeList, List<User> following,
            List<User> followers) {
        super(id, name, cpf, email, password, profilePicPath);
        this.contact = contact;
        this.postList = postList;
        this.eventList = eventList;
        this.likeList = likeList;
        this.following = following;
        this.followers = followers;
    }
    
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public List<Post> getPostList() {
        return postList;
    }
    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
    public List<Event> getEventList() {
        return eventList;
    }
    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
    public List<Like> getLikeList() {
        return likeList;
    }
    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }
    public List<User> getFollowing() {
        return following;
    }
    public void setFollowing(List<User> following) {
        this.following = following;
    }
    public List<User> getFollowers() {
        return followers;
    }
    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    
    
}
