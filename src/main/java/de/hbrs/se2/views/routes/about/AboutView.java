package de.hbrs.se2.views.routes.about;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.views.common.MainLayout;


@PageTitle("About")
@AnonymousAllowed
@Route(value = Constant.Value.Route.ABOUT, layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("About Our Platform");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);

        add(new Paragraph(
                "Our website is dedicated to helping students discover valuable job opportunities, internships, and practical experiences " +
                        "that align with their academic and career goals. We connect students with companies eager to support emerging talent, " +
                        "making it easier than ever to take the first step into the professional world."
        ));

        add(new Paragraph(
                "Whether you're looking for a part-time job, an internship, or your first full-time position, our platform offers a " +
                        "range of curated listings and smart tools to help you succeed."
        ));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
