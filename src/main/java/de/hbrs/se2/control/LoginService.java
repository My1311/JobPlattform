package de.hbrs.se2.control;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.RouteConfiguration;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.model.user.UserRepository;
import de.hbrs.se2.util.Encryption;
import de.hbrs.se2.util.SessionAttributes;
import de.hbrs.se2.views.routes.about.AboutView;
import de.hbrs.se2.views.routes.home.HomeView;
import de.hbrs.se2.views.routes.jobfeed.JobFeedView;
import de.hbrs.se2.views.routes.login.Login;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService{
    //todo: Hier kommt noch der LoginController der authentifizierten Benutzer.

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String email, String password) throws IllegalArgumentException {

        User user =  userRepository.getByEmail(email);
        if (user != null && user.getPassword().equals(Encryption.sha256(password))) {
            SessionAttributes.setCurrentUser(user);
            return true;
        }
        throw new IllegalArgumentException("password dont match");
    }

    static public void setRouteConfiguration() {
        RouteConfiguration.forApplicationScope().setAnnotatedRoute(HomeView.class);
        RouteConfiguration.forApplicationScope().setAnnotatedRoute(AboutView.class);
        RouteConfiguration.forApplicationScope().setAnnotatedRoute(JobFeedView.class);
        RouteConfiguration.forApplicationScope().removeRoute(Login.class);
    }



    public @Nullable User addUser(User user) {
        return this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void delete(User user) {
        this.userRepository.delete(user);
    }
    public boolean checkInput(String username, String password, String password2) {
        if (username.trim().isEmpty()) { // trim() entfernt leerzeichen
            Notification.show("Enter a username");
            return false;
        } else if (password.isEmpty()) {
            Notification.show("Enter a password");
            return false;
        } else if (!password.equals(password2)) {
            Notification.show("passwords don't match");
            return false;
        } else {
            Notification.show("Registry confirmed");
            userRepository.save(new User(username, password));
            return true;
        }

    }
}
