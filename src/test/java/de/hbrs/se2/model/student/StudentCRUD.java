package de.hbrs.se2.model.student;

import de.hbrs.se2.Application;
import de.hbrs.se2.control.user.LoginService;
import de.hbrs.se2.control.location.LocationService;
import de.hbrs.se2.control.student.StudentService;
import de.hbrs.se2.control.user.UserService;
import de.hbrs.se2.model.location.Location;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.util.Encryption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
public class StudentCRUD {

    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private StudentService studentService;

    @Test
    public void testStudentInsert() {
        User user = User.builder().email("test@test.te").password(Encryption.sha256("test")).build();
        Location location = Location.builder().city("Test")
                .country("Test")
                .house_number("1")
                .street("Test")
                .zip_code("1234").build();
        Student student = Student.builder().date_of_birth(Instant.now()).degree("Test").description("test")
                .first_name("TestFirstName")
                .last_name("TestLastName")
                .phone("+4912345678")
                .location(location)
                .major_study("Test")
                .status("Test")
                .degree("Test")
                .user(user).build();
        this.userService.addUser(user);
        this.locationService.addLocation(location);
        this.studentService.addStudent(student);

        System.out.println(this.userService.findAllUsers());
        System.out.println(this.locationService.findAll());
        System.out.println(this.studentService.findAll());

        this.studentService.findStudentByUser(user);

        this.studentService.delete(student);
        this.locationService.delete(student.getLocation());
        this.userService.deleteUser(student.getUser());
    }
}
