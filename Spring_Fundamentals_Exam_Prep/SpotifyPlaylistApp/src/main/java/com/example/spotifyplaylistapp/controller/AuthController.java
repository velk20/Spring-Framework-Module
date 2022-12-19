package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/logout")
    public String logout() {
        if (authService.isLoggedIn()) {
            authService.logout();
        }
        return "redirect:/";
    }
}
