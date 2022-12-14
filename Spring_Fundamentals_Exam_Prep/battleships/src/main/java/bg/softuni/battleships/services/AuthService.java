package bg.softuni.battleships.services;

import bg.softuni.battleships.models.dtos.LoginDTO;
import bg.softuni.battleships.models.dtos.UserRegistrationDTO;
import bg.softuni.battleships.models.entities.User;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final LoggedUser loggedUser;

    public AuthService(UserRepository userRepository, ModelMapper mapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.loggedUser = loggedUser;
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getConfirmPassword().equals(userRegistrationDTO.getPassword())) {
            return false;
        }

        if (this.userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            return false;
        }

        if (this.userRepository.findByUsername(userRegistrationDTO.getUsername()).isPresent()) {
            return false;
        }

        this.userRepository.save(mapper.map(userRegistrationDTO, User.class));
        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> byUsernameAndPassword =
                this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (byUsernameAndPassword.isEmpty()) {
            return false;
        }
        this.loggedUser.login(byUsernameAndPassword.get());

        return true;
    }

}
