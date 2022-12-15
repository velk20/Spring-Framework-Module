package com.likebookapp.model.dto;

import com.likebookapp.util.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match"
)
public class UserRegisterDTO {
    @NotEmpty(message = "Username can not be empty.")
    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters.")
    private String username;
    @NotEmpty(message = "Email can not be empty.")
    @Email
    private String email;
    @NotEmpty(message = "Password can not be empty.")
    @Size(min = 3,max = 20,message = "Password length must be between 3 and 20 characters.")
    private String password;
    @NotEmpty(message = "Confirm Password can not be empty.")
    @Size(min = 3,max = 20,message = "Confirm Password length must be between 3 and 20 characters.")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
