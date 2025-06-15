package de.hbrs.se2.views.routes.profile;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import de.hbrs.se2.control.user.LoginService;
import de.hbrs.se2.control.location.LocationService;
import de.hbrs.se2.control.skill.SkillService;
import de.hbrs.se2.control.student.StudentService;
import de.hbrs.se2.control.user.UserService;
import de.hbrs.se2.model.location.Location;
import de.hbrs.se2.model.skill.Skill;
import de.hbrs.se2.model.student.Student;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.Constant;
import de.hbrs.se2.util.SessionAttributes;
import de.hbrs.se2.views.common.MainLayout;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Profile")
@Route(value = Constant.Value.Route.EDITPROFILE, layout = MainLayout.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@AnonymousAllowed
public class EditStudentProfile extends FormLayout {

    private final DatePicker date_of_birth = new DatePicker("Date Of Birth");
    private final TextField first_name = new TextField("First Name");
    private final TextField last_name = new TextField("Last Name");
    private final TextField phone = new TextField("Phone");
    private final TextField major_study = new TextField("Major Study");
    private final MultiSelectComboBox<Skill> list_of_skills = new MultiSelectComboBox<>();
    private final TextField degree = new TextField("Degree");
    private final TextArea description = new TextArea("Description");
    private final TextField status = new TextField("Status");
    private final TextField street = new TextField("Street");
    private final TextField house_number = new TextField("House Number");
    private final TextField zip_code = new TextField("Postal Code");
    private final TextField city = new TextField("City");
    private final TextField country = new TextField("Country");
    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");
    private final Checkbox switchEditMode = new Checkbox("Edit Profile");
    private final User currentUser = SessionAttributes.getCurrentUser();


    private final UserService userService;
    private final StudentService studentService;
    private final LocationService locationService;
    private final SkillService skillService;
    private Student currentStudent;


    @PostConstruct
    private void initPage() {
        /**
         * when there is not a logged-in user, then he/she has to go login
         */
//        if(this.currentUser == null) {
//            UI.getCurrent().navigate(Constant.Value.Route.LOGIN);
//            add(new Div(new H3("Please login before creating your Profile!")));
//            return;
//        }
        if (this.currentUser != null) {
            this.currentStudent = studentService.findStudentByUser(this.currentUser);
        } else { // When there is not a logged-in user, then he/she has to go login.
            UI.getCurrent().getPage().setLocation(Constant.Value.Route.LOGIN);
            Notification.show("Please Login!", 5, Notification.Position.TOP_CENTER);
            return;
        }
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        Hr hr = new Hr();
        Details details = new Details();
        HorizontalLayout layoutRow = new HorizontalLayout();
        setWidth("100%");
        getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Student Profile from " + this.currentStudent.getFirst_name() + " " + this.currentStudent.getLast_name());
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        phone.setWidth("min-content");
        list_of_skills.setWidth("min-content");
        setMultiSelectComboBoxSampleData(list_of_skills);
        this.list_of_skills.setLabel("Skills");
        major_study.setWidth("min-content");
        degree.setWidth("min-content");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, description);
        description.setWidth("100%");
        details.setWidth("100%");
        setDetailsSampleData(details);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        saveButton.setWidth("min-content");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.saveButton.addClickListener((event) -> saveListener(event));
        cancelButton.setWidth("min-content");
        add(layoutColumn2);
        layoutColumn2.add(h3, switchEditMode);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(first_name, last_name, date_of_birth, phone, status, list_of_skills, major_study, degree);
        layoutColumn2.add(description, hr, details, layoutRow);
        layoutRow.add(saveButton, cancelButton);

        first_name.setRequiredIndicatorVisible(true);
        last_name.setRequiredIndicatorVisible(true);
        major_study.setRequiredIndicatorVisible(true);

        /**
         * reset the input fields to their values from db
         */
        switchEditMode();
        switchEditMode.addClickListener((event) -> switchEditMode());
        cancelButton.addClickListener((event) -> bindInputFieldsWithStudentData());
        bindInputFieldsWithStudentData();
    }

    private boolean requiredFieldsAreFilled() {
        if (this.first_name.getValue().isEmpty()) {
            Notification.show("First name is required!");
            return false;
        }
        if (this.last_name.getValue().isEmpty()) {
            Notification.show("Last name is required!");
            return false;
        }
        if (this.major_study.getValue().isEmpty()) {
            Notification.show("Major Study is required!");
            return false;
        }
        return true;
    }

    private void switchEditMode() {
        if(this.switchEditMode.getValue()){
            this.first_name.setEnabled(true);
            this.last_name.setEnabled(true);
            this.date_of_birth.setEnabled(true);
            this.phone.setEnabled(true);
            this.status.setEnabled(true);
            this.list_of_skills.setEnabled(true);
            this.major_study.setEnabled(true);
            this.degree.setEnabled(true);
            this.description.setEnabled(true);
            this.street.setEnabled(true);
            this.house_number.setEnabled(true);
            this.zip_code.setEnabled(true);
            this.city.setEnabled(true);
            this.country.setEnabled(true);
        }else {
            this.first_name.setEnabled(false);
            this.last_name.setEnabled(false);
            this.date_of_birth.setEnabled(false);
            this.phone.setEnabled(false);
            this.status.setEnabled(false);
            this.list_of_skills.setEnabled(false);
            this.major_study.setEnabled(false);
            this.degree.setEnabled(false);
            this.description.setEnabled(false);
            this.street.setEnabled(false);
            this.house_number.setEnabled(false);
            this.zip_code.setEnabled(false);
            this.city.setEnabled(false);
            this.country.setEnabled(false);
        }
    }

    private void setMultiSelectComboBoxSampleData(@NotNull MultiSelectComboBox<Skill> multiSelectComboBox) {

        List<Skill> allSkills = this.skillService.findAll();
        multiSelectComboBox.setItems(allSkills);
        multiSelectComboBox.setItemLabelGenerator(Skill::getName);

        multiSelectComboBox.addValueChangeListener(e -> {
           String selectedSkillsAsText = e.getValue().stream().map(Skill::getName).collect(Collectors.joining(", "));
        });

    }

    private void setDetailsSampleData(@NotNull Details details) {

        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        FormLayout formLayout2Col = new FormLayout();
        layoutColumn2.setWidthFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignSelf(FlexComponent.Alignment.START, street);
        street.setWidth("100%");
        house_number.setWidth("min-content");
        formLayout2Col.setWidth("100%");
        zip_code.setWidth("min-content");
        this.city.setWidth("min-content");
        country.setWidth("min-content");
        layoutColumn2.add(layoutRow);
        layoutRow.add(street, house_number);
        formLayout2Col.add(zip_code, city, country);
        layoutColumn2.add(formLayout2Col);
        details.setSummaryText("Address information (Optional)");
        details.setOpened(false);
        details.setContent(layoutColumn2);
    }

    private Location writeLocation() {
        return Location.builder().city(this.city.getValue())
                .country(this.country.getValue())
                .house_number(String.valueOf(this.house_number.getValue()))
                .street(this.street.getValue())
                .zip_code(this.zip_code.getValue()).build();
    }
    private Student writeStudent(Location location) {
        return Student.builder()
                .date_of_birth(Instant.now())
                .degree(this.degree.getValue())
                .description(this.description.getValue())
                .first_name(this.first_name.getValue())
                .last_name(this.last_name.getValue())
                .phone(this.phone.getValue())
                .location(location)
                .major_study(this.major_study.getValue())
                .status(this.status.getValue())
                .user(this.currentUser).build();
    }

    private void saveListener(ClickEvent<Button> event) {
        if (requiredFieldsAreFilled()) {
            if (this.currentStudent != null) { // Wenn der benutzer ein student schon erstellt hat
                this.currentStudent.setDegree(this.degree.getValue());
                this.currentStudent.setFirst_name(this.first_name.getValue());
                this.currentStudent.setLast_name(this.last_name.getValue());
                this.currentStudent.setMajor_study(this.major_study.getValue());
                //this.currentStudent.setList_of_skills(this.list_of_skills.getValue()); //todo erstelle mit SkillService ein SKill Liste fuer den Studenten
                this.currentStudent.setStatus(this.status.getValue());
                this.currentStudent.setDescription(this.description.getValue());

                ZoneId zoneId = ZoneId.systemDefault();
                ZonedDateTime now = this.date_of_birth.getValue().atStartOfDay().atZone(zoneId);

                this.currentStudent.setDate_of_birth(now.toInstant());
                this.currentStudent.setPhone(this.phone.getValue());
                Location location = this.currentStudent.getLocation();
                location.setCity(this.city.getValue());
                location.setCountry(this.country.getValue());
                location.setHouse_number(this.house_number.getValue());
                location.setZip_code(this.zip_code.getValue());
                this.currentStudent.setLocation(location);

                this.studentService.addStudent(this.currentStudent);
                this.locationService.addLocation(location);
                Notification.show("Student updated");
            }
        }

    }


    private boolean bindInputFieldsWithStudentData() {
        Student student = this.studentService.findStudentByUser(this.currentUser);
        if(student != null) {
            this.first_name.setValue(student.getFirst_name());
            this.last_name.setValue(student.getLast_name());
            Instant instant = Instant.parse("2020-01-23T00:00:00Z");
            ZoneId zone = ZoneId.of("GMT+1");
            LocalDate birthDate = LocalDate.ofInstant(instant, zone);
            this.date_of_birth.setValue(birthDate);
            this.phone.setValue(student.getPhone());
            this.status.setValue(student.getStatus());
            this.list_of_skills.setValue();
            this.major_study.setValue(student.getMajor_study());
            this.degree.setValue(student.getDegree());
            this.description.setValue(student.getDescription());

            Location location = student.getLocation();
            //Location
            if(location != null) {
                this.street.setValue(location.getStreet());
                this.house_number.setValue(location.getHouse_number());
                this.zip_code.setValue(location.getZip_code());
                this.city.setValue(location.getCity());
                this.country.setValue(location.getCity());
            }
            return true;
        }
        return false;
    }
}
