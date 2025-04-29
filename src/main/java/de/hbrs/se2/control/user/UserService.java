package de.hbrs.se2.control.user;

import de.hbrs.se2.control.company.CompanyService;
import de.hbrs.se2.control.student.StudentService;
import de.hbrs.se2.model.common.BaseEntity;
import de.hbrs.se2.model.company.Company;
import de.hbrs.se2.model.student.Student;
import de.hbrs.se2.model.user.User;
import de.hbrs.se2.model.user.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private CompanyService companyService;
    private StudentService studentService;

    public @Nullable User addUser(User user) {return userRepository.save(user);}

    public @Nullable User findUserByEmail(@NotNull String email) {
        return this.userRepository.findUserByEmail(email);
    }
    public @Nullable User updateUser(@NotNull User user) {
        return this.userRepository.save(user);
    }
    public void deleteUser(@NotNull User user) {
        this.userRepository.delete(user);
    }
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }
    public BaseEntity identifyRole(User user) {
        Company company;
        try {
            company = companyService.findCompanyByUser(user);
        } catch (NoSuchElementException e) {
            company = null;
        }

        Student student;
        try {
            student = studentService.findStudentByUser(user);
        } catch (NoSuchElementException e) {
            student = null;
        }

        return student == null ? company : student;
    }
}
