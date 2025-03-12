package com.equipeAcelera.EventifyAPI.DTOs.user;

import org.springframework.web.multipart.MultipartFile;

public class RegisterOrganizerUserDTO {
    private String name;
    private String email;
    private String cpf;
    private String password;
    private MultipartFile profilePic;
    private String contact;

    public RegisterOrganizerUserDTO(String name, String email, String cpf, String password, MultipartFile profilePic,
            String contact) {
        this.name = name;
        this.email = email;
        this.cpf= cpf;
        this.password = password;
        this.profilePic = profilePic;
        this.contact = contact;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
}
