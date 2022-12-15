package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.LoginUserDTO;
import bg.softuni.pathfinder.model.dto.RegisterUserDTO;
import bg.softuni.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerUserDTO")
    public void initForm(Model model) {
        model.addAttribute("registerUserDTO", new RegisterUserDTO());
    }
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginUserDTO loginUserDTO) {
        userService.loginUser(loginUserDTO);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerView() {
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid RegisterUserDTO registerUserDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        System.out.println(registerUserDTO);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerUserDTO", bindingResult);

            return "redirect:/users/register";
        }
        this.userService.registerUser(registerUserDTO);
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }
}
