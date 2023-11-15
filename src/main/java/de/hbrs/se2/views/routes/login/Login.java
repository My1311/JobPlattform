package de.hbrs.se2.views.routes.login;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.router.RouteConfiguration;
import de.hbrs.se2.control.LoginService;
import de.hbrs.se2.util.Constant;


@PageTitle("Login")
@Route(value = Constant.Value.Route.LOGIN)
public class Login extends Div {

    VerticalLayout loginView;
    FormLayout registryView;


    public Login(LoginService loginService) {
        loginView = createLoginView(loginService);
        registryView = createRegistryView(loginService);
        add(
                new H1("Welcome"),
                buttonBehavior(),
                loginView,
                registryView
        );
    }
    public VerticalLayout createLoginView(LoginService loginService) {
        /*var loginButton = new Button("Login");*/
        var textfieldUsername = new TextField("Username");
        var textfieldPassword = new PasswordField("Password");


        VerticalLayout loginLayout = new VerticalLayout(
                new H1("Login"),
                textfieldUsername,
                textfieldPassword,
                new Button("Submit" , buttonClickEvent -> {
                    try {
                        loginService.authenticate(textfieldUsername.getValue(), textfieldPassword.getValue());
                        //LoginService.setRouteConfiguration();
                        UI.getCurrent().navigate("home");
                    } catch (IllegalArgumentException e) {
                        Notification.show("Wrong Username or Password");
                    }
                })
        );
        loginLayout.setVisible(false); // default invisible
        return loginLayout;
    }

    public FormLayout createRegistryView(LoginService loginService) {
        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        TextField username = new TextField("* Username");
        PasswordField password = new PasswordField("* Password");
        PasswordField confirmPassword = new PasswordField("* Confirm password");

        ComboBox <String> comboBox = new ComboBox<>("User-Type");
        comboBox.setItems("Student", "Company");

        var submitButton = new Button("Submit" ,buttonClickEvent -> {

            if (loginService.checkInput(username.getValue(), password.getValue(), confirmPassword.getValue())) {
                //LoginService.setRouteConfiguration();
                UI.getCurrent().navigate("home");
            }
        });
        submitButton.addClassName("custom-submit-button");


        FormLayout formLayout = new FormLayout();
        formLayout.addClassName("custom-form-layout");
        formLayout.add(
                firstName,
                lastName,
                username,
                comboBox,
                password,
                confirmPassword,
                submitButton
        );


        // Stretch the username field over 2 columns
        /*formLayout.setColspan(username, 2);*/

        formLayout.setColspan(submitButton, 2);
        formLayout.setVisible(false); // default invisible
        return formLayout;
    }

    public HorizontalLayout buttonBehavior() {
        var loginButton = new Button("Login");
        var registryButton = new Button("Registry");

        loginButton.addClickListener(click -> {
            loginView.setVisible(true);
            registryView.setVisible(false);
        });

        registryButton.addClickListener(click -> {
            loginView.setVisible(false);
            registryView.setVisible(true);
        });

        return new HorizontalLayout(loginButton, registryButton);
    }
}

