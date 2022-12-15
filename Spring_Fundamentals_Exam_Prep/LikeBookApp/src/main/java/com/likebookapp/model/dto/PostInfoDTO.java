package com.likebookapp.model.dto;

import com.likebookapp.model.entity.Mood;

public class PostInfoDTO {
    private Long id;
    private String content;
    private long  userLikes;
    private String mood;

    public Long getId() {
        return id;
    }

    public PostInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostInfoDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public long getUserLikes() {
        return userLikes;
    }

    public PostInfoDTO setUserLikes(long userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public PostInfoDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }
}
