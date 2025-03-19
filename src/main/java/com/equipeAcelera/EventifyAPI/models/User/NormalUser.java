package com.equipeAcelera.EventifyAPI.models.User;

import java.time.LocalDate;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;

public class NormalUser extends User{
    private LocalDate birth;
    private List<Subscription> subscriptions;

    

    public NormalUser(int id, String name, String cpf, String email, String password, String profilePicPath, LocalDate birth,
            List<Post> postList, List<Like> likeList, List<User> following, List<User> followers, List<Subscription> subscriptions) {
        super(id, name, cpf, email, password, profilePicPath, postList, likeList, following, followers);
        this.birth = birth;
        this.subscriptions = subscriptions;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
    
}
