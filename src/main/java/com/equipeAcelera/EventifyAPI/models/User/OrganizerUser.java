package com.equipeAcelera.EventifyAPI.models.User;

import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;

public class OrganizerUser extends User{
    private String contact;
    private List<Event> eventList;


    
    public OrganizerUser(int id, String name, String cpf, String email, String password, String profilePicPath, List<Post> postList, String contact,
            List<Event> eventList, List<Like> likeList, List<User> following,
            List<User> followers) {
        super(id, name, cpf, email, password, profilePicPath, postList, likeList, following, followers);
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
    public List<Event> getEventList() {
        return eventList;
    }
    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }    
    
}
