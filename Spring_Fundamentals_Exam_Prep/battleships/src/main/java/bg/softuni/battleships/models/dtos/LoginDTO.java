package bg.softuni.battleships.models.dtos;

import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ToString
public class LoginDTO {
    @NotEmpty
    @Size(min = 3,max = 10)
    private String username;
    @NotEmpty
    @Size(min = 3)
    private String password;

    public LoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public LoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
