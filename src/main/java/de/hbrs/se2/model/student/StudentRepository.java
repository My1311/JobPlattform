package de.hbrs.se2.model.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Student findStudentByUserId(UUID userId);
}
