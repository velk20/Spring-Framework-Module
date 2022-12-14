package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dtos.LoginDTO;
import bg.softuni.battleships.models.dtos.UserRegistrationDTO;
import bg.softuni.battleships.services.AuthService;
import bg.softuni.battleships.services.UserService;
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

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
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
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

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

        System.out.println(loginDTO);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
            return "redirect:/login";
        }

        return "redirect:/";
    }
}
