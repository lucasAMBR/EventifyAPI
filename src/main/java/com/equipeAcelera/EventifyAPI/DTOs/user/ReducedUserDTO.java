package com.equipeAcelera.EventifyAPI.DTOs.user;

public class ReducedUserDTO {
    private int id;
    private String profilePicPath;
    private String name;
    private String type;
    private int followers;
    private int following;
    private int posts;

    public ReducedUserDTO(int id, String profilePicPath, String name, String type, int followers, int following, int posts) {
        this.id = id;
        this.profilePicPath = profilePicPath;
        this.name = name;
        this.type = type;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getFollowers() {
        return followers;
    }
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    public int getFollowing() {
        return following;
    }
    public void setFollowing(int following) {
        this.following = following;
    }
    public int getPosts() {
        return posts;
    }
    public void setPosts(int posts) {
        this.posts = posts;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }
    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }
}
