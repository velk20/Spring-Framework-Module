package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/")
    public String index() {
        if (authService.isLoggedIn()) {
            return "home";
        }
        return "index";
    }

}
