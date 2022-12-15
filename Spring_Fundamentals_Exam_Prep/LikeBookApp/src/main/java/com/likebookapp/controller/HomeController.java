package com.likebookapp.controller;

import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final PostService postService;

    public HomeController(LoggedUser loggedUser, PostService postService) {
        this.loggedUser = loggedUser;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("userPosts", this.postService.getUserPosts(loggedUser.getId()));
        model.addAttribute("otherPosts", this.postService.getOtherUsersPosts(loggedUser.getId()));
        return "home";
    }
}
