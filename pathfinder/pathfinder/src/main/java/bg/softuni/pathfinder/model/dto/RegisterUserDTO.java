package bg.softuni.pathfinder.model.dto;

import lombok.ToString;

import javax.validation.constraints.*;

@ToString
public class RegisterUserDTO {
    @NotEmpty
    @Size(min = 5,max = 20)
    private String username;
    @NotBlank
    @Size(min = 5,max = 20)
    private String fullname;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 5,max = 20)
    private String password;
    @NotEmpty
    @Size(min = 5,max = 20)
    private String confirmPassword;
    @NotNull
    @PositiveOrZero
    @Max(90)
    private Integer age;
    public String getUsername() {
        return username;
    }

    public RegisterUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public RegisterUserDTO setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterUserDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public RegisterUserDTO setAge(Integer age) {
        this.age = age;
        return this;
    }
}
