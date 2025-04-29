package de.hbrs.se2.views.routes.login;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import de.hbrs.se2.control.user.UserService;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.Constant;
import jakarta.annotation.PostConstruct;

import java.util.Set;

@Route(value = Constant.Value.Route.REGISTER)
public class RegisterView extends Div {

    private UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;
    }
    @PostConstruct
    protected Component initContent(){
        TextField username = new TextField("Username");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Confirm Password");
        ComboBox<String> roleSelect = new ComboBox<>("Rolle");
        roleSelect.setItems("Student", "Company", "Admin");


        return new VerticalLayout(
                new H2("Register"),
                username, password1, password2,roleSelect,
                new Button("Send", event -> register (
                        username.getValue(),
                        password1.getValue(),
                        password2.getValue(),
                        roleSelect.getValue()
                )));
    }
    private void register(String username, String password, String confirmPassword, String role){

        if(username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty() || role.trim().isEmpty()){
            Notification.show("Please enter all of the fields.");
        } else if(!password.equals(confirmPassword)){
            Notification.show("Passwords do not match.");
        } else {
            User newUser = new User(username, password, role);
            userService.addUser(newUser);
            Notification.show("User registered successfully.");
        }
    }
}
