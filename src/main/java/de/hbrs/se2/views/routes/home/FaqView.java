package de.hbrs.se2.views.routes.home;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.views.common.MainLayout;

@PageTitle("FAQ")
@AnonymousAllowed
@Route(value = Constant.Value.Route.FAQ, layout = MainLayout.class)
public class FaqView extends VerticalLayout {

    public FaqView() {
        setSpacing(true);

        H2 header = new H2("Frequently Asked Questions");
        add(header);

        add(new Paragraph("Q: Do I need to pay to use the platform?\n" +
                "A: No, our platform is completely free for students."));

        add(new Paragraph("Q: How do I apply for a job?\n" +
                "A: Once you find a listing you're interested in, click on it and use the 'Apply' button to submit your application."));

        add(new Paragraph("Q: Can I update my profile after registration?\n" +
                "A: Yes, you can update your profile information at any time through the 'My Profile' section."));

        add(new Paragraph("Q: What types of jobs are listed?\n" +
                "A: We list part-time jobs, internships, student assistant positions, and entry-level full-time roles."));

        add(new Paragraph("Q: How can I contact support?\n" +
                "A: You can reach out to us via the 'Contact' page or email us at support@studentjobs-hub.com."));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);
        getStyle().set("padding", "2rem");
    }
}