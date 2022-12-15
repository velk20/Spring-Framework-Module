package com.likebookapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    @NotEmpty
    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters.")
    private String username;
    @NotEmpty
    @Size(min = 3,max = 20,message = "Password length must be between 3 and 20 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
