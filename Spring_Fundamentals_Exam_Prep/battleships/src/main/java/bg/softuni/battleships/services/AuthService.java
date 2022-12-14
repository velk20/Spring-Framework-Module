package bg.softuni.battleships.services;

import bg.softuni.battleships.models.dtos.UserRegistrationDTO;
import bg.softuni.battleships.models.entities.User;
import bg.softuni.battleships.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    public AuthService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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
}
