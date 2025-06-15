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
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.hbrs.se2.control.company.CompanyService;
import de.hbrs.se2.control.location.LocationService;
import de.hbrs.se2.control.student.StudentService;
import de.hbrs.se2.control.user.UserService;
import de.hbrs.se2.model.company.Company;
import de.hbrs.se2.model.location.Location;
import de.hbrs.se2.model.student.Student;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.util.Encryption;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;


@AnonymousAllowed
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Route(value = Constant.Value.Route.REGISTER)
public class RegisterView extends Div {

    private final UserService userService;
    private final StudentService studentService;
    private final CompanyService companyService;
    private final LocationService locationService;

    @PostConstruct
    protected void initContent(){
        TextField email = new TextField("E-Mail");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Confirm Password");
        ComboBox<String> roleSelect = new ComboBox<>("Rolle");
        roleSelect.setItems("Student", "Company", "Admin");

        VerticalLayout layout = new VerticalLayout(
                new H2("Register"),
                email, password1, password2,roleSelect,
                new Button("Send", event -> register (
                        email.getValue(),
                        password1.getValue(),
                        password2.getValue(),
                        roleSelect.getValue()
                )));
        layout.setWidthFull();
        setWidthFull();
        add(layout);
    }

    private void register(String username, String password, String confirmPassword, String role){

        if(username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty() || role.trim().isEmpty()){
            Notification.show("Please enter all of the fields.");
        } else if(!password.equals(confirmPassword)){
            Notification.show("Passwords do not match.");
        } else {
            User newUser = new User(username, password, role);
            userService.addUser(newUser);
            // Roles should be saved directly after registration
            // Problem: Wann werden die Daten von Student/Company gespeichert?
            if(role.equals("Student")){
                Location location = Location.builder().build();
                locationService.addLocation(location);
                studentService.addStudent(Student.builder().user(newUser).date_of_birth(Instant.now()).location(location).build());
            } else if(role.equals("Company")){
                companyService.addCompany(Company.builder().user(newUser).build());
            }

            Notification.show("User registered successfully.");
        }
    }
}
