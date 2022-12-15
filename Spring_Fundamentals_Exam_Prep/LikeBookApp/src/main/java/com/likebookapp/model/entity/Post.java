package com.likebookapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "posts")
@Entity
public class Post extends BaseEntity {
    @Column(nullable = false)
    private String content;
    @ManyToOne(optional = false)
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userLikes;
    @ManyToOne(optional = false)
    private Mood mood;

    public Post() {
        this.userLikes = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Post setUser(User user) {
        this.user = user;
        return this;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public Post setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public Mood getMood() {
        return mood;
    }

    public Post setMood(Mood mood) {
        this.mood = mood;
        return this;
    }

    public void addUserLike(User user) {
        this.userLikes.add(user);
    }

    public boolean removeUserLike(User user) {
       return this.userLikes.remove(user);
    }
}
