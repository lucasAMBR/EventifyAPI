package com.equipeAcelera.EventifyAPI.DTOs.user;

import org.springframework.web.multipart.MultipartFile;

public class UpdateUserDataDTO {
    private int id;
    private String userName;
    private MultipartFile newProfilePic;

    public UpdateUserDataDTO(int id,String userName, MultipartFile newProfilePic) {
        this.id = id;
        this.userName = userName;
        this.newProfilePic = newProfilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getNewProfilePic() {
        return newProfilePic;
    }

    public void setNewProfilePic(MultipartFile newProfilePic) {
        this.newProfilePic = newProfilePic;
    }
    
}
