package bg.softuni.pathfinder.model.dto;

public class RegisterUserDTO {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String confirmPassword;
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
