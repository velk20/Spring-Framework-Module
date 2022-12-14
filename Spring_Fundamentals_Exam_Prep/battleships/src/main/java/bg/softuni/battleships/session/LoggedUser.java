package bg.softuni.battleships.session;

import bg.softuni.battleships.models.entities.User;
import org.hibernate.event.internal.ProxyVisitor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private Long id;
    private String fullName;

    public void logout() {
        this.id = null;
        this.fullName = null;
    }

    public void login(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public LoggedUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
