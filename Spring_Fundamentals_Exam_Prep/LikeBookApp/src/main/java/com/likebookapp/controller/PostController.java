package com.likebookapp.controller;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostController {

    private final PostService postService;
    private final LoggedUser loggedUser;

    public PostController(PostService postService, LoggedUser loggedUser) {
        this.postService = postService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addPost")
    public AddPostDTO addPostDTO() {
        return new AddPostDTO();
    }

    @GetMapping("/add")
    public String addPost() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "post-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid AddPostDTO addPostDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPost", addPostDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPost", bindingResult);
            return "redirect:/add";
        }

        this.postService.addPost(addPostDTO);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable("id") long postId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.postService.deletePost(postId);
        return "redirect:/home";
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable("id") long postId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        this.postService.likePost(postId);
        return "redirect:/home";
    }
}
