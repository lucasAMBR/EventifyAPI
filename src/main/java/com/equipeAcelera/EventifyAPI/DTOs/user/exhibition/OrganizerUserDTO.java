package com.equipeAcelera.EventifyAPI.DTOs.user.exhibition;

import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.event.exhibition.EventDTO;
import com.equipeAcelera.EventifyAPI.DTOs.like.exhibition.LikeDTO;
import com.equipeAcelera.EventifyAPI.DTOs.post.exhibition.PostDTO;

public class OrganizerUserDTO {
    private String contact;
    private List<PostDTO> postList;
    private List<LikeDTO> likeList;
    private List<EventDTO> eventList;
    private List<ReducedUserDTO> following;
    private List<ReducedUserDTO> followers;
    
    public OrganizerUserDTO(String contact, List<PostDTO> postList, List<LikeDTO> likeList, List<EventDTO> eventList,
            List<ReducedUserDTO> following, List<ReducedUserDTO> followers) {
        this.contact = contact;
        this.postList = postList;
        this.likeList = likeList;
        this.eventList = eventList;
        this.following = following;
        this.followers = followers;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<PostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }

    public List<LikeDTO> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<LikeDTO> likeList) {
        this.likeList = likeList;
    }

    public List<EventDTO> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventDTO> eventList) {
        this.eventList = eventList;
    }

    public List<ReducedUserDTO> getFollowing() {
        return following;
    }

    public void setFollowing(List<ReducedUserDTO> following) {
        this.following = following;
    }

    public List<ReducedUserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<ReducedUserDTO> followers) {
        this.followers = followers;
    }
    
}
