package com.equipeAcelera.EventifyAPI.models.Post;

import java.time.LocalDateTime;
import java.util.List;

import com.equipeAcelera.EventifyAPI.models.Comments.Comment;
import com.equipeAcelera.EventifyAPI.models.Like.Like;

public class Post {
    protected int id;
    protected int userId;
    protected String userName;
    protected String content;
    protected List<String> imagesPath;
    protected List<Like> likeList;
    protected List<Comment> commentList;
    protected LocalDateTime date;
    
    public Post(int id, int userId, String userName, String content, List<String> imagesPath, List<Like> likeList, List<Comment> commentList, LocalDateTime date) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.imagesPath = imagesPath;
        this.likeList = likeList;
        this.commentList = commentList;
        this.date = date;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

        
}
