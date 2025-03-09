package com.equipeAcelera.EventifyAPI.DTOs.user;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

public class RegisterNormalUserDTO {
    private String name;
    private String email;
    private String password;
    private MultipartFile profilePic;
    private LocalDate birth;
    
    public RegisterNormalUserDTO(String name, String email, String password, MultipartFile profilePic,
            LocalDate birth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.birth = birth;
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
    public MultipartFile getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(MultipartFile profilePic) {
        this.profilePic = profilePic;
    }
    public LocalDate getBirth() {
        return birth;
    }
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    
}
