package de.hbrs.se2.views.routes.home;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.views.common.MainLayout;

@PageTitle("Tutorial")
@AnonymousAllowed
@Route(value = Constant.Value.Route.HOME, layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(false);

        H2 header = new H2("Getting Started – Step-by-Step Tutorial");
        add(header);

        add(new Paragraph("Follow these simple steps to start using the JobPlattform:"));

        OrderedList tutorialSteps = new OrderedList();
        tutorialSteps.add(new ListItem("Create an account or log in using your university credentials."));
        tutorialSteps.add(new ListItem("Complete your profile with details such as your field of study, skills, and availability."));
        tutorialSteps.add(new ListItem("Browse available job and internship listings using the search and filter options."));
        tutorialSteps.add(new ListItem("Save interesting opportunities to your favorites or apply directly from the platform."));
        tutorialSteps.add(new ListItem("Track your applications and receive updates via your dashboard."));
        tutorialSteps.add(new ListItem("Stay informed with notifications about new postings that match your profile."));

        add(tutorialSteps);

        add( new Anchor(Constant.Value.Route.FAQ, new Paragraph("Need more help? Visit our FAQ page or contact support. We’re here to help you succeed!")));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);
        getStyle().set("padding", "2rem");
    }
}
