package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.LoginUserDTO;
import bg.softuni.pathfinder.model.dto.RegisterUserDTO;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final CurrentUser currentUser;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public UserService(CurrentUser currentUser, ModelMapper mapper, UserRepository userRepository) {
        this.currentUser = currentUser;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byUsername = this.userRepository.findByUsername(registerUserDTO.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("username.used");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerUserDTO.getEmail());
        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        User user = mapper.map(registerUserDTO, User.class);

        this.userRepository.save(user);
    }

    public void loginUser(LoginUserDTO loginUserDTO) {
        User user = mapper.map(loginUserDTO, User.class);

        Optional<User> byUsername = userRepository.findByUsername(loginUserDTO.getUsername());
        if (byUsername.isEmpty()) {
            return;
        }

        if (!byUsername.get().getPassword().equals(loginUserDTO.getPassword())) {
            return;
        }

        currentUser.setLoggedIn(true).setName(loginUserDTO.getUsername()).setAdmin(false
        );

    }

    public void logout() {
        currentUser.clear();
    }

}
