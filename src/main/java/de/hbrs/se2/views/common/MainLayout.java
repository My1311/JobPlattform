package de.hbrs.se2.views.common;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.hbrs.se2.control.user.LoginService;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.SessionAttributes;
import de.hbrs.se2.views.routes.about.AboutView;
import de.hbrs.se2.views.routes.home.HomeView;
import de.hbrs.se2.views.routes.jobfeed.JobFeedView;
import de.hbrs.se2.views.routes.login.LoginView;
import de.hbrs.se2.views.routes.login.RegisterView;
import de.hbrs.se2.views.routes.profile.CompanyProfile;
import de.hbrs.se2.views.routes.profile.EditStudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */


@AnonymousAllowed
@PageTitle("Main")
public class MainLayout extends AppLayout {
    private H2 viewTitle;
    private final LoginService loginService;
    private final User currentUser;


    public MainLayout(LoginService loginService) {
        this.loginService = loginService;
        this.currentUser = loginService.getCurrentUser();
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.addLoginListener(loginEvent -> {
            if(this.loginService.authenticate(loginEvent.getUsername(), loginEvent.getPassword())) {
                loginOverlay.close();
                Notification.show("Logged in as " + loginEvent.getUsername());
            } else {
                loginOverlay.setError(true);
            }
        });
        Button login = new Button("Login");
        Button logout = new Button("Logout");
        Button register = new Button("Register");
        login.addClickListener(e -> loginOverlay.setOpened(true));
        logout.addClickListener(buttonClickEvent -> {
           this.loginService.logout();
           Notification.show("Logged out");
           SessionAttributes.reloadSite();
        });
        register.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(RegisterView.class);
        });
        //Button logout = new Button("Log out", e -> securityService.logout());
        addToNavbar(true, toggle, viewTitle);
        if(this.currentUser != null) {
            addToNavbar(logout);
        } else {
            addToNavbar(login, register);
        }
    }

    private void addDrawerContent() {
        H1 appName = new H1("StudentJobCafe");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if(this.currentUser == null) {
            nav.addItem(new SideNavItem("Login", LoginView.class, LineAwesomeIcon.USER.create()));
        } else if(this.currentUser.getRoles().equals("Student")) {
            nav.addItem(new SideNavItem("Profile", EditStudentProfile.class, LineAwesomeIcon.USER.create()));
        } else if(this.currentUser.getRoles().equals("Company")) {
            nav.addItem(new SideNavItem("Profile", CompanyProfile.class, LineAwesomeIcon.USER.create()));
        }
            nav.addItem(new SideNavItem("Home", HomeView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
            nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));
            nav.addItem(new SideNavItem("JobFeed", JobFeedView.class, LineAwesomeIcon.LIST_SOLID.create()));
        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
