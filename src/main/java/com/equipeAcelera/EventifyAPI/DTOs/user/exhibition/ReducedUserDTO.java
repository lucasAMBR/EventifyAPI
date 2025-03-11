package com.equipeAcelera.EventifyAPI.DTOs.user.exhibition;

import java.time.LocalDate;

public class ReducedUserDTO {
    private int id;
    private String type;
    private String name;
    private LocalDate birth;
    private int followers;
    private int following;
    
    public ReducedUserDTO(int id, String type, String name, LocalDate birth, int followers, int following) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.birth = birth;
        this.followers = followers;
        this.following = following;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirth() {
        return birth;
    }
    public void setBirth(LocalDate birth) {
        this.birth = birth;
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

    
}
