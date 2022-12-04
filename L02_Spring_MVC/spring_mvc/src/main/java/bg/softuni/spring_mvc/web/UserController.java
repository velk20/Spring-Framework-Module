package bg.softuni.spring_mvc.web;

import bg.softuni.spring_mvc.model.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping()
    public String newUser() {
        return "createUser";
    }
    @PostMapping()
    public String createUser(UserDTO userDTO) {
        System.out.println("Creating new user..." + userDTO);
        return "createdUser";
    }
}
