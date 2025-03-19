package com.equipeAcelera.EventifyAPI.DTOs.user;

import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.post.ReducedPostDTO;
import com.equipeAcelera.EventifyAPI.models.Like.Like;

public class UserDTO {
    protected int id;
    protected String name;
    protected String cpf;
    protected String profilePicPath;
    protected List<ReducedPostDTO> postList;
    protected List<Like> likelist;
    protected List<ReducedUserDTO> following;
    protected List<ReducedUserDTO> followers;
    
    public UserDTO(int id, String name, String cpf, String profilePicPath, List<ReducedPostDTO> postList,
            List<Like> likelist, List<ReducedUserDTO> following, List<ReducedUserDTO> followers) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.profilePicPath = profilePicPath;
        this.postList = postList;
        this.likelist = likelist;
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

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public List<ReducedPostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<ReducedPostDTO> postList) {
        this.postList = postList;
    }

    public List<Like> getLikelist() {
        return likelist;
    }

    public void setLikelist(List<Like> likelist) {
        this.likelist = likelist;
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
