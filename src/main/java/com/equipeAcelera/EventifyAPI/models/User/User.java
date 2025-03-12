package com.equipeAcelera.EventifyAPI.models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    protected int id;
    protected String name;
    protected String email;
    @JsonIgnore
    protected String password;
    protected String profilePicPath;
    
    public User() {
    }

    public User(int id, String name, String email, String password, String profilePicPath) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicPath = profilePicPath;
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
    
}
