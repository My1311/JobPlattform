package de.hbrs.se2.control.user;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.RouteConfiguration;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.model.user.UserRepository;
import de.hbrs.se2.util.Encryption;
import de.hbrs.se2.util.SessionAttributes;
import de.hbrs.se2.views.routes.about.AboutView;
import de.hbrs.se2.views.routes.home.HomeView;
import de.hbrs.se2.views.routes.jobfeed.JobFeedView;
import de.hbrs.se2.views.routes.login.LoginView;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService{

    private final UserService userService;
    //private User currentUser = null;

    public boolean authenticate(@NotNull String email,@NotNull String password) throws IllegalArgumentException {

        final User user =  this.userService.findUserByEmail(email);
        if (user != null && user.getPassword().equals(Encryption.sha256(password))) {
            SessionAttributes.setCurrentUser(user);
          //  this.currentUser = user;
            return true;
        }
        throw new IllegalArgumentException("Password dont match.");
    }

    public @Nullable User getCurrentUser() {
        return SessionAttributes.getCurrentUser();
    }
    public void logout() {
        SessionAttributes.setCurrentUser(null);
    }



//    public boolean checkInput(String username, String password, String password2) {
//        if (username.trim().isEmpty()) { // trim() entfernt leerzeichen
//            Notification.show("Enter a username");
//            return false;
//        } else if (password.isEmpty()) {
//            Notification.show("Enter a password");
//            return false;
//        } else if (!password.equals(password2)) {
//            Notification.show("passwords don't match");
//            return false;
//        } else {
//            Notification.show("Registry confirmed");
//            userRepository.save(new User(username, password));
//            return true;
//        }

}
