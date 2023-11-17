package de.hbrs.se2.views.routes.login;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;
import de.hbrs.se2.util.Constant;

@Route(value = Constant.Value.Route.LOGIN)
public class LoginView extends Div {
    public LoginView() {
        LoginI18n i18nLogin = LoginI18n.createDefault();
        i18nLogin.setAdditionalInformation("Please, contact admin@company.com if you're experiencing issues logging into your account");
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setI18n(i18nLogin);
        add(loginOverlay);
        loginOverlay.setOpened(true);
        loginOverlay.getElement().setAttribute("no-autofocus", "");
        loginOverlay.setAction("login");
    }
}
