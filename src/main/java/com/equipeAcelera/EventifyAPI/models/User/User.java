package com.equipeAcelera.EventifyAPI.models.User;

import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    protected int id;
    protected String name;
    protected String cpf;
    protected String email;
    @JsonIgnore
    protected String password;
    protected String profilePicPath;
    protected List<Post> postList;
    protected List<Like> likeList;
    protected List<User> following;
    protected List<User> followers;

    public User(int id, String name, String cpf, String email, String password, String profilePicPath, List<Post> postList,
            List<Like> likeList, List<User> following, List<User> followers) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.profilePicPath = profilePicPath;
        this.postList = postList;
        this.likeList = likeList;
        this.following = following;
        this.followers = followers;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getProfilePicPath() {
        return profilePicPath;
    }
    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
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
    public List<Post> getPostList() {
        return postList;
    }
    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    
    
}
