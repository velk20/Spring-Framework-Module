package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.LoginUserDTO;
import bg.softuni.pathfinder.model.dto.RegisterUserDTO;
import bg.softuni.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/register")
    public String registerView() {
        return "register";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }
    @PostMapping("/login")
    public String login(LoginUserDTO loginUserDTO) {
        userService.loginUser(loginUserDTO);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(RegisterUserDTO registerUserDTO) {
        userService.registerUser(registerUserDTO);
        return "redirect:/users/login";
    }
}
