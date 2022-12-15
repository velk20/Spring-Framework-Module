package bg.softuni.pathfinder.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private String name;
    private boolean loggedIn;
    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public CurrentUser setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        return this;
    }

    public boolean isAnonymous() {
        return !isLoggedIn();
    }

    public void clear() {
        loggedIn = false;
        name = null;
    }
}
