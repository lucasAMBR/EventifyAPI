package com.equipeAcelera.EventifyAPI.DTOs.user;

import java.time.LocalDate;
import java.util.List;

import com.equipeAcelera.EventifyAPI.DTOs.post.ReducedPostDTO;
import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;

public class NormalUserDTO extends UserDTO {
    private LocalDate birth;
    private List<Subscription> subscriptionList;
    
    public NormalUserDTO(int id, String name, String cpf, String profilePicPath, List<ReducedPostDTO> postList,
            List<Like> likelist, List<ReducedUserDTO> following, List<ReducedUserDTO> followers, LocalDate birth,
            List<Subscription> subscriptionList) {
        super(id, name, cpf, profilePicPath, postList, likelist, following, followers);
        this.birth = birth;
        this.subscriptionList = subscriptionList;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    
}
