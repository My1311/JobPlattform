package de.hbrs.se2.model.student;

import de.hbrs.se2.model.location.LocationDTO;
import de.hbrs.se2.model.user.UserDTO;
import lombok.AllArgsConstructor;

import java.time.Instant;


@AllArgsConstructor
public class StudentDTO {
    private UserDTO user;
    private LocationDTO location;
    private Instant date_of_birth;
    private String first_name;
    private String last_name;
    private String phone;
    private String major_study;
    private String degree;
    private String description;
    private String status;


    public static StudentDTO toDto(Student student) {
        StudentDTO retStudent = new StudentDTO(UserDTO.toDto(student.getUser()),
                LocationDTO.toDto(student.getLocation()),
                student.getDate_of_birth(),
                student.getFirst_name(),
                student.getLast_name(),
                student.getPhone(),
                student.getMajor_study(),
                student.getDegree(),
                student.getDescription(),
                student.getStatus());
        return retStudent;
    }
}
