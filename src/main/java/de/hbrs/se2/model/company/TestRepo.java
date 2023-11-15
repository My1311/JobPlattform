package de.hbrs.se2.model.company;

import de.hbrs.se2.model.location.Location;
import de.hbrs.se2.model.student.Student;
import de.hbrs.se2.model.student.StudentRepository;
import de.hbrs.se2.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;


@SpringBootTest //um alle jpa beans fpr den test zu laden bsp. autowired

public class TestRepo {
    @Autowired
    //CompanyRepository companyRepository;
    //CompanyRepository s;

    @Test
    void saveMethod(){
            //Company c = new Company("3","sdf","wewe","df", 23);
            //s.save(c);


    }


}
