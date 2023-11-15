package de.hbrs.se2.control.student;

import de.hbrs.se2.model.student.Student;
import de.hbrs.se2.model.student.StudentDTO;
import de.hbrs.se2.model.student.StudentRepository;
import de.hbrs.se2.model.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    public StudentDTO findStudentByUserID(UUID userId) {
////        return StudentDTO.toDto(this.studentRepository.findStudentByUserID(userId));
//    }

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
