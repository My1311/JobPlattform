package de.hbrs.se2.views.routes.profile;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;

import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.hbrs.se2.control.user.LoginService;
import de.hbrs.se2.control.user.UserService;
import de.hbrs.se2.control.company.CompanyService;

import de.hbrs.se2.model.company.Company;

import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.Constant;

import elemental.json.Json;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@AnonymousAllowed
@Lazy
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Route(value = Constant.Value.Route.EDITCOMPANYPROFILE/*,registerAtStartup = false*/)
public class CompanyProfile extends VerticalLayout {

    private final CompanyService companyService;
    private final LoginService loginService;
    private final UserService userService;
   // private final RatingService ratingService;
    private final TextField companyNameField = new TextField("Company Name");
    private final TextField industryField = new TextField("Industry");
    private final TextArea descriptionField = new TextArea("Description");
    private final Button editButton = new Button("edit");
    private final Button saveButton = new Button("save");
    private final Button cancelButton = new Button("cancel");
    private final H4 uploadtext = new H4("Update your profile picture");
    private final NativeLabel maxsizelabel = new NativeLabel("Maximum file size: 10 MB");
    private final NativeLabel emptyFieldsError = new NativeLabel("Fields can't be empty!");
    private final MemoryBuffer buffer = new MemoryBuffer();
    private final Upload upload = new Upload(buffer);
  //  private final Grid<Rating> ratingGrid = new Grid<>();
    private final Button showEvaluationButton = new Button("Show Evaluation!");

    private User currentUser;
    private Company company = null;
    private InputStream inputStream;
    Image placeholderImage;

    private byte[] byteOfInput;

    @PostConstruct
    public void init() {
        this.currentUser = loginService.getCurrentUser();

        setSpacing(true);

        setCompanyValues();
        this.company = companyService.findCompanyByUser(Objects.requireNonNull(loginService.getCurrentUser()));


        if(this.company != null && this.company.getLogo() != null) {
            byte[] image = companyService.getLogoByCompany(this.company);
            StreamResource resource = new StreamResource(this.company.getName() +".jpg", () -> new ByteArrayInputStream(image));
            placeholderImage = new Image(resource, this.company.getName());
        }
        placeholderImage = new Image("https://cdn.pixabay.com/photo/2017/11/10/05/24/add-2935429_1280.png", "logo");
        placeholderImage.setWidth("200px");
        placeholderImage.setHeight("200px");
        placeholderImage.getStyle().set("border-radius", "50%");

        add(placeholderImage);

        setCompanyValues();
    //    showRatingStars();

        companyNameField.setReadOnly(true);
        industryField.setReadOnly(true);
        descriptionField.setReadOnly(true);


        descriptionField.setWidth("440px");
        descriptionField.setHeight("300px");


        upload.addClassName("register-company-button-2");
        upload.addSucceededListener(event -> {
            inputStream = buffer.getInputStream();  // Mit dem ContinueButton wird das Logo der Company gesetzt
            try {
                byteOfInput = inputStream.readAllBytes();
            } catch (IOException e) {   // wenn inputstream voreilig geschlossen wurde, bspw. beim einlesen vom inputStream während Netwerkproblemen
                UI.getCurrent().getPage().reload();
            }
            showPic();
        });

        int maxFileSizeInBytes = 10 * 1024 * 1024; // 10MB
        upload.setMaxFileSize(maxFileSizeInBytes);
        uploadtext.setVisible(false);
        maxsizelabel.setVisible(false);
        maxsizelabel.addClassName("maxsizelabel");
        upload.setVisible(false);   // erst nach dem Klicken von edit freigeschaltet

        emptyFieldsError.addClassName("emptyFieldsError");
        emptyFieldsError.setVisible(false);

        setAlignItems(Alignment.CENTER);

        VerticalLayout fieldlayout1 = new VerticalLayout(companyNameField, industryField);
        HorizontalLayout fieldlayout2 = new HorizontalLayout(placeholderImage, fieldlayout1);
       // ratingGrid.setSizeFull();
        VerticalLayout fieldLayoutdesc = new VerticalLayout(fieldlayout2, descriptionField, showEvaluationButton);
        VerticalLayout fieldLayout = new VerticalLayout();

        fieldLayout.add(
                uploadtext,
                maxsizelabel,
                upload
        );

        HorizontalLayout finalLayout = new HorizontalLayout();
        finalLayout.add(fieldLayoutdesc, fieldLayout);


        editButton.addClickListener(event -> onEditButtonClick());
        saveButton.addClickListener(event -> {
            if (companyNameField.getValue().equals("") || industryField.getValue().equals("") || descriptionField.getValue().equals("")) {
                emptyFieldsError.setVisible(true);
            } else {
                this.company.setName(companyNameField.getValue());
                this.company.setIndustry(industryField.getValue());
                this.company.setDescription(descriptionField.getValue());
                this.companyService.addCompany(this.company);
                if (inputStream != null) {
                    this.companyService.setLogoByCompany(company, byteOfInput);
                    upload.getElement().setPropertyJson("files", Json.createArray());   // nach dem save, wird das bereits geuploadete Bild aus der Uploadliste entfernt
                }
                onSaveButtonClick();
            }
        });

        cancelButton.addClickListener(event -> {
 //           getOldPic();
            companyNameField.setValue(company.getName());
            industryField.setValue(company.getIndustry());
            descriptionField.setValue(company.getDescription());
            companyNameField.setReadOnly(true);
            industryField.setReadOnly(true);
            descriptionField.setReadOnly(true);
            upload.setVisible(false);
            uploadtext.setVisible(false);
            maxsizelabel.setVisible(false);
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            editButton.setVisible(true);
            emptyFieldsError.setVisible(false);
        });
        saveButton.setVisible(false);
        cancelButton.setVisible(false);

//        showEvaluationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        showEvaluationButton.addClickListener(event -> show3EvaluatuonText());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(Alignment.END);
        HorizontalLayout ErrorLayout = new HorizontalLayout();
        ErrorLayout.add(emptyFieldsError);

        if (currentUser != null) {
            if (userService.identifyRole(currentUser) instanceof Company) {
                horizontalLayout.add(editButton);
                horizontalLayout.add(cancelButton);
                horizontalLayout.add(saveButton);
            }
        }
        add(
                finalLayout,
                ErrorLayout,
                horizontalLayout
        );
    }

//    private void show3EvaluatuonText() {
//        ratingGrid.setHeight("100%");
//        ratingGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT, GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
//        //grid.addComponentColumn(evaluation -> createCard(evaluation));
//        //grid.addColumn(rating -> rating.getStudent().getFirst_name() + " " + rating.getStudent().getLast_name()).setHeader("Name");
//        ratingGrid.addColumn(Rating::getText).setHeader("Evaluation");
//        List<Rating> ratings = new ArrayList<>();
//        var li = ratingService.findAllOfRatingInCompany(company);
//        for (int i = 0; i < li.size() && i < 3; i++) {
//            ratings.add(li.get(i));
//        }
//
//        System.out.println(ratings.size());
//        System.out.println(ratings.get(0));
//        ListDataProvider<Rating> ratingList = new ListDataProvider<>(ratings);
//        ratingGrid.setItems(ratingList);
//        ratingGrid.getDataProvider().refreshAll();
//    }

//
//
//
//    private void showRatingStars() {
//        RatingGenerator ratingGenerator = new RatingGenerator();
//        add(ratingGenerator.getRatingDisplay(this.companyService.getRatingPunkt(company)));
//    }
//
//    private void getOldPic() {
////        System.out.println("COMPANY  GET IMAGE IST: " + (company.getLogo() == null));
//        // Folgender Fall fehlt: ich tu ein bild rein aber drücke dann cancel. Dann soll das alte Bild wieder kommen.
//        company = companyService.getCompanyByEmail(currentUser.getEmail()); // arbeite mit dem neuen stand, denn Logo kann geupdated sein
//        if(company.getLogo() == null){
//            placeholderImage.setSrc("https://cdn.pixabay.com/photo/2017/11/10/05/24/add-2935429_1280.png");
//        } else {
//            System.out.println("DU BIST HIER");
//            StreamResource res = new StreamResource("oldPic", () -> new ByteArrayInputStream(company.getLogo()));
//            placeholderImage.setSrc(res);
//        }
//        upload.getElement().setPropertyJson("files", Json.createArray());   //bisher hochgeladenes soll nicht mehr angezeigt werden
//    }

//    private ByteArrayInputStream picToByteArrayInputStream() {
//        BufferedImage picture = new BufferedImage("https://cdn.pixabay.com/photo/2017/11/10/05/24/add-2935429_1280.png", "Placeholder Image");
//        ByteArrayOutputStream bytPic = new ByteArrayOutputStream();
//        ImageIO.write(bytPic,"png",picture);
//    }

    private void showPic() {
        StreamResource res = new StreamResource("newPic", () -> new ByteArrayInputStream(byteOfInput));
        placeholderImage.setSrc(res);
    }

    public void setCompanyValues() {
        try {
            this.currentUser = loginService.getCurrentUser();
            company = companyService.findCompanyByUser(currentUser);
            companyNameField.setValue(company.getName());
            industryField.setValue(company.getIndustry());
            descriptionField.setValue(company.getDescription());
            if(company.getLogo() == null) {
                placeholderImage.setSrc("https://cdn.pixabay.com/photo/2017/11/10/05/24/add-2935429_1280.png");
            } else {
                StreamResource res = new StreamResource("oldPic", () -> new ByteArrayInputStream(company.getLogo()));
                placeholderImage.setSrc(res);
            }
        } catch (Exception e) {
            companyNameField.setValue("name");
            industryField.setValue("industry");
            descriptionField.setValue("description");
        }
    }

    public void updateUI(Company company) {
        this.editButton.setVisible(false);
        this.companyNameField.setValue(company.getName());
        this.industryField.setValue(company.getIndustry());
        this.descriptionField.setValue(company.getDescription());
    }

    public void resetUI() {
        this.editButton.setVisible(true);
        setCompanyValues();
    }

    private void onEditButtonClick() {
        companyNameField.setReadOnly(false);
        industryField.setReadOnly(false);
        descriptionField.setReadOnly(false);
        editButton.setVisible(false);
        uploadtext.setVisible(true);
        maxsizelabel.setVisible(true);
        upload.setVisible(true);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
        Notification.show("Your profile is now editable");
    }

    private void onSaveButtonClick() {
        companyNameField.setReadOnly(true);
        industryField.setReadOnly(true);
        descriptionField.setReadOnly(true);
        editButton.setVisible(true);
        emptyFieldsError.setVisible(false);
        upload.setVisible(false);
        uploadtext.setVisible(false);
        maxsizelabel.setVisible(false);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        Notification.show("Changes saved!");
    }
}