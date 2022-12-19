package com.example.spotifyplaylistapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongController {
    @GetMapping("/add")
    public String addSong() {
        return "song-add";
    }
}
