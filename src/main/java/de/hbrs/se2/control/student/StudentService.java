package de.hbrs.se2.control.student;

import de.hbrs.se2.model.student.Student;
import de.hbrs.se2.model.student.StudentRepository;
import de.hbrs.se2.model.user.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public @Nullable Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student findStudentByUser(User user) {
        return this.studentRepository.findStudentByUserId(user.getId());
    }
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    public void delete(Student student) {
        this.studentRepository.delete(student);
    }
}
