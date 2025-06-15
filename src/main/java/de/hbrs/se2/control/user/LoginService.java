package de.hbrs.se2.control.user;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.RouteConfiguration;
import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.model.student.Student;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.model.user.UserRepository;
import de.hbrs.se2.util.Encryption;
import de.hbrs.se2.util.SessionAttributes;
import de.hbrs.se2.views.routes.profile.CompanyProfile;
import de.hbrs.se2.views.routes.profile.EditStudentProfile;
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
        System.out.println(user.getPassword());
        System.out.println(Encryption.sha256(password));
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

    public void navigate(BaseEntity identity) {
        if (identity instanceof Student) {
            UI.getCurrent().navigate(EditStudentProfile.class);
        } else {
            UI.getCurrent().navigate(CompanyProfile.class);
        }
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
