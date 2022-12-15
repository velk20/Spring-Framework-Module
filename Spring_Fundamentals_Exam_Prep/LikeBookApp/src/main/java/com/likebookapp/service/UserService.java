package com.likebookapp.service;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final LoggedUser loggedUser;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    public UserService(LoggedUser loggedUser, ModelMapper mapper, UserRepository userRepository) {
        this.loggedUser = loggedUser;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {
        Optional<User> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        Optional<User> byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }
        User user = mapper.map(userRegisterDTO, User.class);
        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsernameAndPassword =
                this.userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (byUsernameAndPassword.isEmpty()) {
            return false;
        }
        
        User user = byUsernameAndPassword.get();
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
        return true;
    }

    public void logout() {
        this.loggedUser.logout();

    }
}
