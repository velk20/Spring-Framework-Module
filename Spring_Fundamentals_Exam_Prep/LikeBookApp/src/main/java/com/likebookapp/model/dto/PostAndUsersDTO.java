package com.likebookapp.model.dto;

public class PostAndUsersDTO {
    private String content;
    private long  userLikes;
    private String mood;
    private String creater;
    private Long id;

    public Long getId() {
        return id;
    }

    public PostAndUsersDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostAndUsersDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public long getUserLikes() {
        return userLikes;
    }

    public PostAndUsersDTO setUserLikes(long userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public PostAndUsersDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }

    public String getCreater() {
        return creater;
    }

    public PostAndUsersDTO setCreater(String creater) {
        this.creater = creater;
        return this;
    }
}
