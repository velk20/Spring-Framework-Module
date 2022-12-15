package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dtos.LoginDTO;
import bg.softuni.battleships.models.dtos.UserRegistrationDTO;
import bg.softuni.battleships.services.AuthService;
import bg.softuni.battleships.services.UserService;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final LoggedUser loggedUser;

    public AuthController(UserService userService, AuthService authService, LoggedUser loggedUser) {
        this.userService = userService;
        this.authService = authService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegisterDTO() {
        return new UserRegistrationDTO();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }

        System.out.println(registrationDTO);
        if (bindingResult.hasErrors() || !this.authService.register(registrationDTO)) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (loggedUser.getId() != null) {
            return "redirect:/index";
        }

        System.out.println(loginDTO);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
            return "redirect:/login";
        }

        if (!this.authService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (loggedUser.getId() == null) {
            return "redirect:/index";
        }

        this.loggedUser.logout();
        return "index";
    }
}
