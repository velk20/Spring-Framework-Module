package bg.softuni.battleships.services;

import bg.softuni.battleships.models.dtos.UserRegistrationDTO;
import bg.softuni.battleships.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
