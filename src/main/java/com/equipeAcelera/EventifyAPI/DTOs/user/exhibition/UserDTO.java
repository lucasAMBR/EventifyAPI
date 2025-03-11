package com.equipeAcelera.EventifyAPI.DTOs.user.exhibition;

public class UserDTO {
    protected int id;
    protected String name;
    protected String email;
    protected String profilePicPath;
    
    public UserDTO(int id, String name, String email, String profilePicPath) {
        this.id = id;
        this.name = name;
        this.email = email;
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
    public String getProfilePicPath() {
        return profilePicPath;
    }
    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    
}
