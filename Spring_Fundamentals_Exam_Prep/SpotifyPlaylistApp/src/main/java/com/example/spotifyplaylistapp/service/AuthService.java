package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final LoggedUser loggedUser;

    public AuthService(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLoggedIn() {
        return loggedUser.getId() != null;
    }

    public void logout() {
        loggedUser.logout();
    }

    public boolean register() {
        return false;
    }

    public boolean login() {
        return false;
    }
}
