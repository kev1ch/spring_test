package edu.spring_practice.jpa.repositories;

import edu.spring_practice.jpa.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void saveStudentTest() {
        // Student test_student = new Student();
    }

}
