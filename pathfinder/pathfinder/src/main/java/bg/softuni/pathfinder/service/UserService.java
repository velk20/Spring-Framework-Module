package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.LoginUserDTO;
import bg.softuni.pathfinder.model.dto.RegisterUserDTO;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService appUserDetailsService;

    public UserService(ModelMapper mapper, UserRepository userRepository
            , PasswordEncoder passwordEncoder, UserDetailsService appUserDetailsService) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
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
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return  this.userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found!"));
    }

    public void loginUser(LoginUserDTO loginUserDTO) {
        User user = mapper.map(loginUserDTO, User.class);

        Optional<User> byUsername = userRepository.findByUsername(loginUserDTO.getUsername());
        if (byUsername.isEmpty()) {
            return;
        }

        if (!passwordEncoder.matches(loginUserDTO.getPassword(), byUsername.get().getPassword())) {
            return;
        }

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(byUsername.get().getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);

    }

}
