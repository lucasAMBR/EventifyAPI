package com.equipeAcelera.EventifyAPI.models.User;

import java.time.LocalDate;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;

public class NormalUser extends User{
    private LocalDate birth;
    private List<Post> postList;
    private List<Like> likeList;
    private List<User> following;
    private List<User> followers;
    private List<Subscription> subscriptions;

    

    public NormalUser(int id, String name, String email, String password, String profilePicPath, LocalDate birth,
            List<Post> postList, List<Like> likeList, List<User> following, List<User> followers, List<Subscription> subscriptions) {
        super(id, name, email, password, profilePicPath);
        this.birth = birth;
        this.postList = postList;
        this.likeList = likeList;
        this.following = following;
        this.followers = followers;
        this.subscriptions = subscriptions;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
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

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
    
}
