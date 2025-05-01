package de.hbrs.se2.views.routes.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.hbrs.se2.control.user.LoginService;
import de.hbrs.se2.control.user.UserService;
import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.util.Encryption;
import de.hbrs.se2.views.component.HeaderLayout;
import de.hbrs.se2.views.routes.home.HomeView;
import de.hbrs.se2.views.routes.profile.CompanyProfile;
import de.hbrs.se2.views.routes.profile.EditStudentProfile;


import java.util.NoSuchElementException;


@AnonymousAllowed
@PageTitle("Login")
@Route(value = Constant.Value.Route.LOGIN)
public class LoginView extends LoginForm {
    private final LoginService loginService;

    public LoginView(LoginService loginService) {
        this.loginService = loginService;
        addLoginListener(loginEvent -> {
           if(this.loginService.authenticate(loginEvent.getUsername(), loginEvent.getPassword())) {
                Notification.show(loginEvent.getUsername() + " logged in");
                UI.getCurrent().navigate(HomeView.class);
           } else {
               setError(true);
               Notification.show(loginEvent.getUsername() + " failed");
           }
        });

    }
}