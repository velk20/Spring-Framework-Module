package bg.softuni.battleships.models.dtos;

import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ToString
public class UserRegistrationDTO {
    @NotEmpty
    @Size(min = 3, max = 10)
    private String username;
    @NotEmpty
    @Size(min = 5, max = 20)
    private String fullName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3)
    private String password;
    @NotEmpty
    @Size(min = 3)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRegistrationDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
