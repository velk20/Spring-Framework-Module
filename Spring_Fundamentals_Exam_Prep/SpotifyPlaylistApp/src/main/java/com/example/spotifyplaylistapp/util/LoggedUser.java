package com.example.spotifyplaylistapp.util;

import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private Long id;
    private String username;

    public void logout() {
        this.id = null;
        this.username = null;
    }

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return username;
    }

    public LoggedUser setFullName(String fullName) {
        this.username = fullName;
        return this;
    }
}
