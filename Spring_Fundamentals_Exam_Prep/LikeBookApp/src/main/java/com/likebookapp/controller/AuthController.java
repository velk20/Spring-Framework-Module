package com.likebookapp.controller;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final LoggedUser loggedUser;
    private final UserService userService;

    public AuthController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO loginDTO() {
        return new UserLoginDTO();
    }
    @GetMapping("/login")
    public String login() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "register";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        this.userService.logout();
        return "redirect:/";
    }
    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            return "redirect:/login";
        }

        if (!this.userService.login(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("invalidCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.register(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/register";
        }

        return "redirect:/login";
    }


}
