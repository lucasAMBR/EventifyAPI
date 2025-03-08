package com.equipeAcelera.EventifyAPI.models.Post;

import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Like.Like;

public class Post {
    protected int id;
    protected int userId;
    protected String content;
    protected List<String> imagesPath;
    protected List<Like> likeList;
    
    public Post(int id, int userId, String content, List<String> imagesPath, List<Like> likeList) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.imagesPath = imagesPath;
        this.likeList = likeList;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public List<String> getImagesPath() {
        return imagesPath;
    }
    public void setImagesPath(List<String> imagesPath) {
        this.imagesPath = imagesPath;
    }
    public List<Like> getLikeList() {
        return likeList;
    }
    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }

    
}
