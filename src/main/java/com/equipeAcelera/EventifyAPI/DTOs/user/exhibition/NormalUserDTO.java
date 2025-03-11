package com.equipeAcelera.EventifyAPI.DTOs.user.exhibition;

import java.time.LocalDate;
import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.like.exhibition.LikeDTO;
import com.equipeAcelera.EventifyAPI.DTOs.post.exhibition.PostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.subscription.exhibition.SubscriptionDTO;

public class NormalUserDTO extends UserDTO{
    private LocalDate birth;
    private List<PostDTO> postList;
    private List<LikeDTO> likeList;
    private List<ReducedUserDTO> following;
    private List<ReducedUserDTO> followers;
    private List<SubscriptionDTO> subscriotions;
    
    public NormalUserDTO(int id, String name, String email, String profilePicPath, LocalDate birth,
            List<PostDTO> postList, List<LikeDTO> likeList, List<ReducedUserDTO> following,
            List<ReducedUserDTO> followers, List<SubscriptionDTO> subscriotions) {
        super(id, name, email, profilePicPath);
        this.birth = birth;
        this.postList = postList;
        this.likeList = likeList;
        this.following = following;
        this.followers = followers;
        this.subscriotions = subscriotions;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
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

    public List<SubscriptionDTO> getSubscriotions() {
        return subscriotions;
    }

    public void setSubscriotions(List<SubscriptionDTO> subscriotions) {
        this.subscriotions = subscriotions;
    }

    
}
