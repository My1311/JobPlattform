package de.hbrs.se2.views.routes.home;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.views.common.MainLayout;
import de.hbrs.se2.views.routes.login.LoginView;

@PageTitle("Home")
@Route(value=Constant.Value.Route.HOME, layout = MainLayout.class)
public class HomeView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HomeView() {
        TextArea textArea = new TextArea();
        textArea.setWidth("100%");
        textArea.setHeight("100%");
        textArea.setReadOnly(true);
        textArea.setValue("Willkommen zu meiner Website. Hier kannst du neue Jobangebote finden :)) \nWenn du noch kein Benutzerkonto hast, melde dich hier an oder erstelle ein Konto!");
      //  Button buttonLogin = new Button("Login");
      //  LoginView loginView = new LoginView();
     //   buttonLogin.addClickListener(e -> {loginView.setOpened(true);});
     //   add(textArea, loginView, buttonLogin);
        add(textArea);
    }

}
